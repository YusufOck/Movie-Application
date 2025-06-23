// AddToWatchLaterUseCase: Sonradan izlenecekler listesine film ekler.
// Repository ile ViewModel arasında köprü görevi görür.
package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.model.db.WatchLaterEntity
import com.example.retrofit_usage.repository.WatchLaterRepository

class AddToWatchLaterUseCase(private val repository: WatchLaterRepository) {
    suspend operator fun invoke(movie: WatchLaterEntity) = repository.insert(movie)
} 