package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.repository.MovieLocalDataSource
import com.example.retrofit_usage.model.db.MovieEntity

// InsertMovieUseCase: Veritabanına film eklemek için kullanılır.
// Room'a veri ekleme işlemini soyutlar.
class InsertMovieUseCase(private val local: MovieLocalDataSource) {
    suspend fun execute(movie: MovieEntity)
            = local.insertMovie(movie)
}