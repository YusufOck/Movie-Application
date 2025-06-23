// WatchLaterViewModelFactory: WatchLaterViewModel nesnesini oluşturur.
// ViewModelProvider ile ViewModel'ın bağımlılıklarını sağlar.
package com.example.retrofit_usage.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_usage.viewmodel.WatchLaterViewModel

class WatchLaterViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WatchLaterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WatchLaterViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 