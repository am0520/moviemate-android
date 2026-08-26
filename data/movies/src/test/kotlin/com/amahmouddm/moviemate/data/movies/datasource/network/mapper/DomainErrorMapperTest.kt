package com.amahmouddm.moviemate.data.movies.datasource.network.mapper

import com.amahmouddm.moviemate.data.movies.model.DomainError
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.ContentConvertException
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.io.IOException
import kotlin.test.assertFailsWith

class DomainErrorMapperTest {
    @Test
    fun `maps IOException to connection failed`() {
        // Given
        val exception = IOException()

        // When
        val result = exception.toDomainError()

        // Then
        assertEquals(
            expected = DomainError.CONNECTION_FAILED,
            actual = result,
        )
    }

    @Test
    fun `maps unknown exception to unknown`() {
        // Given
        val exception = IllegalStateException()

        // When
        val result = exception.toDomainError()

        // Then
        assertEquals(
            expected = DomainError.UNKNOWN,
            actual = result,
        )
    }

    @Test
    fun `maps timeout exceptions to request timed out`() {
        // Given
        val exceptions = listOf(
            HttpRequestTimeoutException(
                url = "https://example.com",
                timeoutMillis = 1_000L,
            ),
            ConnectTimeoutException("Connection timed out"),
            SocketTimeoutException("Socket timed out"),
        )

        exceptions.forEach { exception ->
            // When
            val result = exception.toDomainError()

            // Then
            assertEquals(
                expected = DomainError.REQUEST_TIMED_OUT,
                actual = result,
            )
        }
    }

    @Test
    fun `maps content convert exception to invalid data`() {
        // Given
        val exception = ContentConvertException("Invalid response body")

        // When
        val result = exception.toDomainError()

        // Then
        assertEquals(
            expected = DomainError.INVALID_DATA,
            actual = result,
        )
    }

    @Test
    fun `maps no transformation found exception to invalid data`() = runTest {
        HttpClient(
            MockEngine {
                respond(
                    content = "",
                )
            }
        ).use { client ->
            // Given
            val exception = assertFailsWith<NoTransformationFoundException> {
                client
                    .get("https://example.com")
                    .body<UnsupportedBody>()
            }

            // When
            val result = exception.toDomainError()

            // Then
            assertEquals(
                expected = DomainError.INVALID_DATA,
                actual = result,
            )
        }
    }

    @Test
    fun `maps response status codes to domain errors`() = runTest {
        // Given
        val cases = listOf(
            HttpStatusCode.Unauthorized to DomainError.ACCESS_DENIED,
            HttpStatusCode.Forbidden to DomainError.ACCESS_DENIED,
            HttpStatusCode.NotFound to DomainError.NOT_FOUND,
            HttpStatusCode.RequestTimeout to DomainError.REQUEST_TIMED_OUT,
            HttpStatusCode.TooManyRequests to DomainError.RATE_LIMITED,
            HttpStatusCode.BadRequest to DomainError.INVALID_REQUEST,
            HttpStatusCode.MethodNotAllowed to DomainError.INVALID_REQUEST,
            HttpStatusCode.NotAcceptable to DomainError.INVALID_REQUEST,
            HttpStatusCode.UnprocessableEntity to DomainError.INVALID_REQUEST,
            HttpStatusCode.InternalServerError to DomainError.SERVICE_UNAVAILABLE,

            // A response status that isn't explicitly handled
            HttpStatusCode.Conflict to DomainError.UNKNOWN,
        )

        cases.forEach { (statusCode, expectedError) ->
            val client = HttpClient(
                MockEngine {
                    respond(
                        content = "",
                        status = statusCode,
                    )
                }
            ) {
                expectSuccess = true
            }

            client.use { client ->
                // When
                val exception = assertFailsWith<ResponseException> {
                    client.get("https://example.com")
                }

                val result = exception.toDomainError()

                // Then
                assertEquals(
                    expected = expectedError,
                    actual = result,
                    message = "Failed for status $statusCode",
                )
            }
        }
    }

    private data class UnsupportedBody(val value: String)
}
