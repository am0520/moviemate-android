package com.amahmouddm.moviemate.data.movies.datasource.network.mapper

import com.amahmouddm.moviemate.data.movies.datasource.network.result.SERVER_ERROR_STATUS_CODE_MAX
import com.amahmouddm.moviemate.data.movies.datasource.network.result.SERVER_ERROR_STATUS_CODE_MIN
import com.amahmouddm.moviemate.data.movies.model.DomainError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.ContentConvertException
import kotlinx.io.IOException

internal fun Exception.toDomainError(): DomainError = when (this) {
    is ResponseException -> response.status.toDomainError()

    // These must be checked before IOException.
    is HttpRequestTimeoutException,
    is ConnectTimeoutException,
    is SocketTimeoutException,
        -> DomainError.REQUEST_TIMED_OUT

    is ContentConvertException,
    is NoTransformationFoundException,
        -> DomainError.INVALID_DATA

    is IOException -> DomainError.CONNECTION_FAILED

    else -> DomainError.UNKNOWN
}

private val serverErrorStatusRange =
    SERVER_ERROR_STATUS_CODE_MIN..SERVER_ERROR_STATUS_CODE_MAX

private fun HttpStatusCode.toDomainError(): DomainError = when {
    this == HttpStatusCode.Unauthorized ||
            this == HttpStatusCode.Forbidden -> DomainError.ACCESS_DENIED

    this == HttpStatusCode.NotFound -> DomainError.NOT_FOUND

    this == HttpStatusCode.RequestTimeout -> DomainError.REQUEST_TIMED_OUT

    this == HttpStatusCode.TooManyRequests -> DomainError.RATE_LIMITED

    this == HttpStatusCode.BadRequest ||
            this == HttpStatusCode.MethodNotAllowed ||
            this == HttpStatusCode.NotAcceptable ||
            this == HttpStatusCode.UnprocessableEntity -> DomainError.INVALID_REQUEST

    value in serverErrorStatusRange -> DomainError.SERVICE_UNAVAILABLE

    else -> DomainError.UNKNOWN
}
