// WatchLaterRepository: Sonradan izlenecek filmler için veri yönetimini sağlar.
// DAO ile ViewModel/usecase arasında köprü görevi görür.
package com.example.retrofit_usage.repository

import androidx.lifecycle.LiveData
import com.example.retrofit_usage.model.db.WatchLaterDao
import com.example.retrofit_usage.model.db.WatchLaterEntity

class WatchLaterRepository(private val dao: WatchLaterDao) {
    suspend fun insert(movie: WatchLaterEntity) = dao.insert(movie)
    suspend fun delete(movie: WatchLaterEntity) = dao.delete(movie)
    fun getAll(): LiveData<List<WatchLaterEntity>> = dao.getAll()
} 