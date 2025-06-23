package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.repository.WatchLaterRepository
//watchlaterRepodaakı getAll fonsıyonunu cagırıyot
// GetWatchLaterMoviesUseCase: Sonradan izlenecek filmleri listeler.
// Repository'den verileri çeker ve ViewModel'e iletir.
class GetWatchLaterMoviesUseCase(private val repository: WatchLaterRepository) {
    operator fun invoke() = repository.getAll()
} 