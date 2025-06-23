package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.repository.MovieRemoteDataSource
import com.example.retrofit_usage.model.db.MovieListResponse
import retrofit2.Response

// FetchPopularMoviesUseCase: Popüler filmleri API'den çekmek için kullanılır.
// API çağrısını soyutlar ve ViewModel/Repository'ye veri sağlar.

class FetchPopularMoviesUseCase(private val remote: MovieRemoteDataSource) {
    suspend fun execute(apiKey: String, page: Int = 1, language: String): Response<MovieListResponse> {
        return remote.getPopularMovies(apiKey, page, language)
    }
}