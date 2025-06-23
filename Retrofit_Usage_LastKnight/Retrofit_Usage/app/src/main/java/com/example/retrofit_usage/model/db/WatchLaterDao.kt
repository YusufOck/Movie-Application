package com.example.retrofit_usage.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete

// WatchLaterDao: Sonradan izlenecek filmler için Room DAO arayüzü.
// Ekleme, silme ve listeleme işlemlerini tanımlar.
@Dao
interface WatchLaterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: WatchLaterEntity)

    @Query("SELECT * FROM sonradan_izle")
    fun getAll(): LiveData<List<WatchLaterEntity>>

    @Delete
    suspend fun delete(movie: WatchLaterEntity)
} 