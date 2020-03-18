package com.example.itunesgrabber.model.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunesgrabber.model.cache.database.dao.AlbumsDAO
import com.example.itunesgrabber.model.remote.itunes.entity.AlbumItemEntity

/**
 * Is responsible for create room database instance
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Database(
    entities = [AlbumItemEntity::class],
    version = 5,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        /** @since 20200318 v1: provides database instance */
        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "room_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}