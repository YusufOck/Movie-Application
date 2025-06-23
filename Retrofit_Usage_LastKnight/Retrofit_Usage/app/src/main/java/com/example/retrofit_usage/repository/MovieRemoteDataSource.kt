package com.example.retrofit_usage.repository

import com.example.retrofit_usage.model.api.MovieService
import com.example.retrofit_usage.model.api.RetrofitInstance
import com.example.retrofit_usage.model.db.MovieListResponse
import com.example.retrofit_usage.model.db.MovieResponse
import retrofit2.Response

// MovieRemoteDataSource: Uzaktaki API ile veri alışverişini yönetir.
// API'den film verilerini çeker ve repository'ye iletir.

class MovieRemoteDataSource {
    private val movieService = RetrofitInstance.getRetrofitInstance().create(MovieService::class.java)

    suspend fun getPopularMovies(apiKey: String, page: Int = 1, language: String): Response<MovieListResponse> {
        return movieService.getPopularMovies("Bearer $apiKey", page, language)
    }

    suspend fun getMovieDetails(id: Int, apiKey: String, language: String): Response<MovieResponse> {
        return movieService.getMovieDetails(id, "Bearer $apiKey", language)
    }
}