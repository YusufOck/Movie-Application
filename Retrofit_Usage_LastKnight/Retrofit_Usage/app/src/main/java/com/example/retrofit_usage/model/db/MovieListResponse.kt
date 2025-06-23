// MovieListResponse: API'den gelen film listesi ve sayfalama bilgisini tutar.
// JSON yanıtını modele çevirir.
package com.example.retrofit_usage.model.db

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) 