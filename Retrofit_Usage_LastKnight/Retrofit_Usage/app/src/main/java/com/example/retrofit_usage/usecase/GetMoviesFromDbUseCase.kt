package com.example.retrofit_usage.usecase

import androidx.lifecycle.LiveData
import com.example.retrofit_usage.repository.MovieLocalDataSource
import com.example.retrofit_usage.model.db.MovieEntity

// GetMoviesFromDbUseCase: Veritabanından film listesini çekmek için kullanılır.
// Room sorgusunu soyutlar ve ViewModel/Repository'ye veri sağlar.

class GetMoviesFromDbUseCase(private val local: MovieLocalDataSource) {
    fun execute(): LiveData<List<MovieEntity>> {
        return local.getAllMovies()
    }
}