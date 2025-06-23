package com.example.retrofit_usage.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

// MovieDao: Room için film veritabanı işlemlerini tanımlar.
// Filmlerle ilgili ekleme, sorgulama ve getirme işlemlerini içerir.
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity?>
}