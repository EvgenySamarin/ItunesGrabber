package com.example.itunesgrabber.model.remote.services

import android.content.Context
import androidx.annotation.Keep
import com.example.itunesgrabber.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Singleton factory to create retrofit api instance
 *
 * @author EvgenySamarin
 * @since 20200314 v1
 */
@Keep
object ServiceGenerator {
    /**
     * Предоставляет сервис для доступа к REST API
     *
     * @param context контекст приложения, необходим для установки http кеширования
     * @param isDebug в дебаг режиме происходит логирование запросов, в продакшн логирования нет
     */
    @UnstableDefault
    fun makeRetrofit(isDebug: Boolean, context: Context): Retrofit = makeRetrofit(
        makeOkHttpClient(
            makeLoggingInterceptor(isDebug), context
        )
    )

    /** создаём экземпляр базового интерфейса ApiService */
    @UnstableDefault
    private fun makeRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val jsonConfig = JsonConfiguration(ignoreUnknownKeys = true)
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(Json(jsonConfig).asConverterFactory(contentType))
            .build()
    }

    /** создаем перехватчика для логирования REST запросов доступного во время отладки */
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    /** создаём клиента с помощью предоставленного перехватчика логов для отладки */
    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor, context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val builder = OkHttpClient.Builder()
            .cache(myCache)  //параметры кеша
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        return builder.build()
    }
}