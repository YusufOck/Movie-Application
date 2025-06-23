// FilterMoviesUseCase: Sadece filtreleme iş mantığını üstlenir.
// Verilen film listesi ve arama sorgusuna göre filtrelenmiş bir liste döner.
package com.example.retrofit_usage.usecase

import com.example.retrofit_usage.model.db.MovieEntity

class FilterMoviesUseCase {
    fun execute(movies: List<MovieEntity>, query: String): List<MovieEntity> {
        if (query.isBlank()) return movies
        return movies.filter { it.title.contains(query, ignoreCase = true) }
    }
} 