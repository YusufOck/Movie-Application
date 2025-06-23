package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.repository.MovieRemoteDataSource
import com.example.retrofit_usage.model.db.MovieResponse
import retrofit2.Response

// GetMovieDetailsUseCase: Bir filmin detaylarını API'den çekmek için kullanılır.
// API çağrısını soyutlar ve ViewModel/Repository'ye veri sağlar.

class GetMovieDetailsUseCase(private val remote: MovieRemoteDataSource) {
    suspend fun execute(id: Int, apiKey: String, language: String): Response<MovieResponse> {
        return remote.getMovieDetails(id, apiKey, language)
    }
}