package com.example.retrofit_usage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofit_usage.model.db.AppDatabase
import com.example.retrofit_usage.model.db.MovieEntity
import com.example.retrofit_usage.model.db.MovieResponse
import com.example.retrofit_usage.repository.MovieLocalDataSource
import com.example.retrofit_usage.repository.MovieRemoteDataSource
import com.example.retrofit_usage.usecase.*
import kotlinx.coroutines.launch
import com.example.retrofit_usage.usecase.FilterMoviesUseCase
import com.example.retrofit_usage.usecase.ObserveFilteredMoviesUseCase
import com.example.retrofit_usage.usecase.GetMovieByIdUseCase
import java.util.Locale

// MovieViewModel: UI ile UseCase katmanı arasında köprü görevi görür.
// Sadece UseCase'leri çağırır ve LiveData yönetir, iş mantığı içermez.

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val localDataSource = MovieLocalDataSource(db)
    private val remoteDataSource = MovieRemoteDataSource()

    private val fetchPopularMoviesUseCase = FetchPopularMoviesUseCase(remoteDataSource)
    private val getMovieDetailsUseCase = GetMovieDetailsUseCase(remoteDataSource)
    private val insertMovieUseCase = InsertMovieUseCase(localDataSource)
    private val getMoviesFromDbUseCase = GetMoviesFromDbUseCase(localDataSource)
    private val filterMoviesUseCase = FilterMoviesUseCase()
    private val observeFilteredMoviesUseCase = ObserveFilteredMoviesUseCase(getMoviesFromDbUseCase.execute(), filterMoviesUseCase)
    private val getMovieByIdUseCase = GetMovieByIdUseCase(localDataSource)

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    val popularMovies: LiveData<List<MovieEntity>> = getMoviesFromDbUseCase.execute()
    val filteredMovies: LiveData<List<MovieEntity>> = observeFilteredMoviesUseCase.getFilteredMovies()

    fun setSearchQuery(query: String) {
        observeFilteredMoviesUseCase.setQuery(query)
    }

    fun fetchPopularMovies(apiKey: String, page: Int = 1) {
        if (isLoading || isLastPage) return
        isLoading = true
        val language = Locale.getDefault().language
        viewModelScope.launch {
            val response = fetchPopularMoviesUseCase.execute(apiKey, page, language)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    if (it.page >= it.totalPages) isLastPage = true
                    it.results.forEach { movie ->
                        insertMovieUseCase.execute(movie.toEntity())
                    }
                    currentPage = it.page
                }
            }
            isLoading = false
        }
    }

    fun loadNextPage(apiKey: String) {
        if (!isLastPage && !isLoading) {
            fetchPopularMovies(apiKey, currentPage + 1)
        }
    }

    fun getMovieDetails(id: Int, apiKey: String) {
        val language = Locale.getDefault().language
        viewModelScope.launch {
            val response = getMovieDetailsUseCase.execute(id, apiKey, language)
            if (response.isSuccessful) {
                response.body()?.let { movie ->
                    insertMovieUseCase.execute(movie.toEntity())
                }
            }
        }
    }

    fun getMovieById(id: Int): LiveData<MovieEntity?> = getMovieByIdUseCase.execute(id)

    private fun MovieResponse.toEntity(): MovieEntity {
        return MovieEntity(
            id = this.id,
            title = this.title,
            releaseDate = this.releaseDate,
            overview = this.overview,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount,
            posterPath = this.posterPath,
            runtime = this.runtime
        )
    }
}