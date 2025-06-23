package com.example.retrofit_usage.model.api

import com.example.retrofit_usage.model.db.MovieListResponse
import com.example.retrofit_usage.model.db.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
// MovieService: Retrofit ile API'ye istek atan arayüzdür.
// MovieRemoteDataSource ile bağlantılıdır.
interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String
    ): Response<MovieListResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Query("language") language: String//dile göre getiricek
    ): Response<MovieResponse>
}