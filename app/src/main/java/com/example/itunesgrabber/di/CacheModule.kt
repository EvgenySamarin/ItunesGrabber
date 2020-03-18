package com.example.itunesgrabber.di

import android.content.Context
import androidx.annotation.Keep
import com.example.itunesgrabber.model.cache.database.AppRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides dependencies of cache layer
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Module
@Keep
class CacheModule {
    /** @since 20200318 v1: provides room database */
    @Provides
    @Singleton
    fun provideAppRoomDatabase(context: Context): AppRoomDatabase =
        AppRoomDatabase.getDatabase(context)
}