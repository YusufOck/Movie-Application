package com.example.retrofit_usage.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// WatchLaterDatabase: Sonradan izlenecek filmler için Room veritabanı.
// WatchLaterDao üzerinden veritabanı işlemlerini yönetir.
@Database(entities = [WatchLaterEntity::class], version = 2, exportSchema = false)
abstract class WatchLaterDatabase : RoomDatabase() {
    abstract fun watchLaterDao(): WatchLaterDao

    companion object {
        @Volatile
        private var INSTANCE: WatchLaterDatabase? = null

        fun getDatabase(context: Context): WatchLaterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WatchLaterDatabase::class.java,
                    "sonradan_izle"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 