package com.example.retrofit_usage.model.db

import com.google.gson.annotations.SerializedName

// MovieResponse: API'den gelen tekil film detaylarını temsil eder.
// Filmle ilgili JSON yanıtını modele çevirir.
data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("runtime") val runtime: Int?
)