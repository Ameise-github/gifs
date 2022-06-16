package com.example.gifs.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gifs.db.favorites.FavoritesDao
import com.example.gifs.db.favorites.FavoritesEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        FavoritesEntity::class
    ]
)
abstract class GifDatabase : RoomDatabase() {

    companion object {
        private const val dbName = "gifs_db"
        const val FAVORITES_TABLE = "favorites"

        @Volatile
        private var instance: GifDatabase? = null

        fun getInstance(application: Application): GifDatabase {
            synchronized(this) {
                return instance ?: instance ?: createDatabaseInstance(application)
            }
        }

        private fun createDatabaseInstance(application: Application): GifDatabase {
            return Room
                .databaseBuilder(
                    application,
                    GifDatabase::class.java,
                    dbName
                )
                .build()
        }
    }

    abstract fun favoritesDao(): FavoritesDao
}