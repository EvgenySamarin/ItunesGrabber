package com.example.itunesgrabber.model.remote.core

/**
 * Base Class for marking errors/failures/exceptions.
 *
 * @author EvgenySamarin
 * @since 20200318 v1
 */
sealed class Failure {
    object ServerError : Failure()
    object NoContentError : Failure()
    object NetworkConnectionError : Failure()
}