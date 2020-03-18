package com.example.itunesgrabber.di

import android.content.Context
import androidx.annotation.Keep
import com.example.itunesgrabber.BuildConfig
import com.example.itunesgrabber.model.remote.core.Request
import com.example.itunesgrabber.model.remote.itunes.ITunesRemote
import com.example.itunesgrabber.model.remote.itunes.ITunesRemoteImpl
import com.example.itunesgrabber.model.remote.services.ApiService
import com.example.itunesgrabber.model.remote.services.ServiceGenerator
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Provides dependencies of remote layer
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Module
@Keep
class RemoteModule {
    /** @since 20200317 v1: provide base Retrofit class with configure OkHTTPClient */
    @UnstableDefault
    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit =
        ServiceGenerator.makeRetrofit(BuildConfig.DEBUG, context)

    /** @since 20200317 v1: provide ITunesApi */
    @UnstableDefault
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    /** provide dependency iTunes remote */
    @Singleton
    @Provides
    fun provideITunesRemote(request: Request, apiService: ApiService): ITunesRemote =
        ITunesRemoteImpl(request, apiService)

}