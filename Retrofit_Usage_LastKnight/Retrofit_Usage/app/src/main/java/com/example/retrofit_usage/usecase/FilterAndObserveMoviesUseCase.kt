// ObserveFilteredMoviesUseCase: Sadece LiveData yönetimi ve combine işlemini üstlenir.
// Filtreleme işini FilterMoviesUseCase'e delege eder.
package com.example.retrofit_usage.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.retrofit_usage.model.db.MovieEntity

class ObserveFilteredMoviesUseCase(
    private val moviesSource: LiveData<List<MovieEntity>>,
    private val filterMoviesUseCase: FilterMoviesUseCase
) {
    private val result = MediatorLiveData<List<MovieEntity>>()
    private var currentQuery: String = ""

    init {
        result.addSource(moviesSource) { filterAndPost() }
    }

    fun getFilteredMovies(): LiveData<List<MovieEntity>> = result

    fun setQuery(query: String) {
        currentQuery = query
        filterAndPost()
    }

    private fun filterAndPost() {
        val movies = moviesSource.value ?: emptyList()
        result.value = filterMoviesUseCase.execute(movies, currentQuery)
    }
} 