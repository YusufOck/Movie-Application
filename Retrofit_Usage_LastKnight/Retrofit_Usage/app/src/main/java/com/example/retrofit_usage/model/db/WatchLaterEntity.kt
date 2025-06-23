package com.example.retrofit_usage.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// WatchLaterEntity: Sonradan izlenecek filmler için Room entity'si.
// Sadece id, başlık ve poster bilgisini tutar.
@Entity(tableName = "sonradan_izle")
data class WatchLaterEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?
) 