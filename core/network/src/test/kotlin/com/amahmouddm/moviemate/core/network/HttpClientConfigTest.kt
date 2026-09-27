package com.amahmouddm.moviemate.core.network

import com.amahmouddm.moviemate.core.common.ServerConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeoutCapability
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpClientConfigTest {

    @Test
    fun `client uses configured timeouts`() = runTest {
        val engine = MockEngine { request ->
            val timeout = requireNotNull(
                request.getCapabilityOrNull(HttpTimeoutCapability)
            )

            assertEquals(10_000L, timeout.connectTimeoutMillis)
            assertEquals(15_000L, timeout.socketTimeoutMillis)
            assertEquals(15_000L, timeout.requestTimeoutMillis)

            respond(
                content = "{}",
                status = HttpStatusCode.OK,
            )
        }

        val client = HttpClient(engine) {
            configureNetworkClient(
                serverConfig = ServerConfig.create(
                    baseUrl = "",
                    accessToken = "",
                )
            )
        }

        client.get("https://example.com")
    }

}
