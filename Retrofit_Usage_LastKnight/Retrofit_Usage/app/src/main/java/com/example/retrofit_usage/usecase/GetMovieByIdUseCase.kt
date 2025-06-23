// GetMovieByIdUseCase: Belirli bir id'ye sahip filmi veritabanından çeker.
// Sadece tek bir film için LiveData döner.
package com.example.retrofit_usage.usecase

import androidx.lifecycle.LiveData
import com.example.retrofit_usage.model.db.MovieEntity
import com.example.retrofit_usage.repository.MovieLocalDataSource

class GetMovieByIdUseCase(private val local: MovieLocalDataSource) {
    fun execute(id: Int): LiveData<MovieEntity?> {
        return local.getMovieById(id)
    }
} 