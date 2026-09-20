package com.amahmouddm.moviemate.core.datatest

import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeServer @Inject constructor() {

    private val responses = ArrayDeque<FakeResponse>()

    val requests = mutableListOf<HttpRequestData>()

    fun enqueueResponse(
        body: String,
        status: HttpStatusCode = HttpStatusCode.OK,
        headers: Headers = headersOf(
            HttpHeaders.ContentType,
            ContentType.Application.Json.toString(),
        ),
    ) {
        responses += FakeResponse(body, status, headers)
    }

    internal fun handle(request: HttpRequestData): FakeResponse {
        requests += request

        return responses.removeFirstOrNull()
            ?: error("No fake response configured")
    }

    internal data class FakeResponse(
        val body: String,
        val status: HttpStatusCode,
        val headers: Headers,
    )
}
