// WatchLaterViewModel: Sonradan izlenecek filmler için ViewModel'dir.
// UseCase'ler ile UI arasında veri akışını yönetir.
package com.example.retrofit_usage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofit_usage.model.db.WatchLaterDatabase
import com.example.retrofit_usage.model.db.WatchLaterEntity
import com.example.retrofit_usage.repository.WatchLaterRepository
import com.example.retrofit_usage.usecase.AddToWatchLaterUseCase
import com.example.retrofit_usage.usecase.GetWatchLaterMoviesUseCase
import kotlinx.coroutines.launch

class WatchLaterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WatchLaterRepository
    private val addToWatchLaterUseCase: AddToWatchLaterUseCase
    private val getWatchLaterMoviesUseCase: GetWatchLaterMoviesUseCase
    val watchLaterMovies: LiveData<List<WatchLaterEntity>>

    init {
        val dao = WatchLaterDatabase.getDatabase(application).watchLaterDao()
        repository = WatchLaterRepository(dao)
        addToWatchLaterUseCase = AddToWatchLaterUseCase(repository)
        getWatchLaterMoviesUseCase = GetWatchLaterMoviesUseCase(repository)
        watchLaterMovies = getWatchLaterMoviesUseCase()
    }

    fun addToWatchLater(movie: WatchLaterEntity) {
        viewModelScope.launch {
            addToWatchLaterUseCase(movie)
        }
    }

    fun deleteFromWatchLater(movie: WatchLaterEntity) {
        viewModelScope.launch {
            repository.delete(movie)
        }
    }
} 