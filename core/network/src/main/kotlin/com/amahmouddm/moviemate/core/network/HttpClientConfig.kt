package com.amahmouddm.moviemate.core.network

import com.amahmouddm.moviemate.core.common.ServerConfig
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun HttpClientConfig<*>.configureNetworkClient(
    serverConfig: ServerConfig,
) {
    expectSuccess = true

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            },
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
        connectTimeoutMillis = 10_000
        socketTimeoutMillis = 15_000
    }

    defaultRequest {
        url(serverConfig.baseUrl)
        header(HttpHeaders.Authorization, "Bearer ${serverConfig.accessToken}")
        accept(ContentType.Application.Json)
    }
}
