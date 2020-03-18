package com.example.itunesgrabber.di

import android.content.Context
import androidx.annotation.Keep
import com.example.itunesgrabber.model.cache.database.AppRoomDatabase
import com.example.itunesgrabber.model.remote.itunes.ITunesRemote
import com.example.itunesgrabber.model.repository.itunes.ITunesRepository
import com.example.itunesgrabber.model.repository.itunes.ITunesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Top app level dagger module. Provides base dependencies like as context and repositories
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Module
@Keep
class AppModule(private val context: Context) {
    /** @since 20200318 v1: provide base app context */
    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    /** @since 20200318 v1: provide ITunes repository */
    @Provides
    @Singleton
    fun provideITunesRepository(
        remote: ITunesRemote, database: AppRoomDatabase
    ): ITunesRepository = ITunesRepositoryImpl(remote, database)

}