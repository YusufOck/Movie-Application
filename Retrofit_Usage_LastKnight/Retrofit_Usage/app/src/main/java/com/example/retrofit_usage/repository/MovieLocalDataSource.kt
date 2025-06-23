package com.example.retrofit_usage.repository

import com.example.retrofit_usage.model.db.AppDatabase
import com.example.retrofit_usage.model.db.MovieEntity

// MovieLocalDataSource: Yerel veritabanı işlemlerini yönetir.
// Room üzerinden film verilerini okur ve yazar.

class MovieLocalDataSource(private val db: AppDatabase) {
    fun getAllMovies() = db.movieDao().getAllMovies()
    fun getMovieById(id: Int) = db.movieDao().getMovieById(id)
    suspend fun insertMovie(movie: MovieEntity) = db.movieDao().insertMovie(movie)
}