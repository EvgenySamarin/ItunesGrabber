package com.example.itunesgrabber.model.remote.core

import com.example.itunesgrabber.model.remote.core.extension.isSucceed
import com.example.itunesgrabber.model.remote.core.extension.parseError
import retrofit2.Call
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Выполняет сетевые запросы. А также производит базовую их обработку. В том числе обработку
 * поступивших ошибок.
 *
 * @author EvgenySamarin
 * @since 20190905 v1
 */
@Singleton
class Request @Inject constructor() {
    /** вспомогательная функция для проверки сети и вызова fun execute */
    fun <T, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return execute(call, transform)
    }

    /**
     * Выполняет сетевой запрос с помощью переданного в параметрах call (call.execute()).
     * В блоке catch формирует маркеры ошибок для дальнейшей обработки
     */
    private fun <T, R> execute(
        call: Call<T>,
        transform: (T) -> R
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            Timber.d("return data: ${response.body()}")
            when (response.isSucceed()) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Timber.d("Catch error: ${exception.fillInStackTrace()}")
            when (exception) {
                //BACKLOG different type of errors here
                is UnknownHostException -> Either.Left(Failure.NetworkConnectionError)
                is SocketTimeoutException -> Either.Left(Failure.NetworkConnectionError)
                else -> Either.Left(Failure.ServerError)
            }
        }
    }
}