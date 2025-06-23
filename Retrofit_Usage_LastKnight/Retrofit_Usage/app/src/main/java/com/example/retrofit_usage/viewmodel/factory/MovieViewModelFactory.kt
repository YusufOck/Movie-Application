// MovieViewModelFactory: MovieViewModel nesnesini oluşturmak için kullanılır.
// ViewModelProvider ile ViewModel'ın bağımlılıklarını sağlar.
package com.example.retrofit_usage.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_usage.viewmodel.MovieViewModel

class MovieViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}