// MovieEntity: Room veritabanı için film tablosunu temsil eder.
// Filmle ilgili tüm temel alanları içerir.
package com.example.retrofit_usage.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
    val posterPath: String?,
    val runtime: Int?
)