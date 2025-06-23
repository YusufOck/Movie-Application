package com.example.retrofit_usage.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance: API bağlantısı için Retrofit nesnesi oluşturur.
// Uygulamanın tüm ağ isteklerinde kullanılacak Retrofit instance'ını sağlar.
class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}