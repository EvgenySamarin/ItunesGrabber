package com.example.itunesgrabber.model.remote.core.extension

import com.example.itunesgrabber.model.remote.core.Failure
import retrofit2.Response
import timber.log.Timber

/** Checks response to success */
fun <T> Response<T>.isSucceed(): Boolean = isSuccessful && body() != null

/** Parses error from REST API */
fun <T> Response<T>.parseError(): Failure {
    Timber.d("Error message: $this")
    return when (code()) {
        204 -> Failure.NoContentError
        else -> Failure.ServerError
    }
}