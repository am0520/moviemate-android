package com.amahmouddm.moviemate.data.movies.datasource.network

import com.amahmouddm.moviemate.core.common.ServerConfig
import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.core.network.configureNetworkClient
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class KtorMoviesRemoteDataSourceTest {

    @Test
    fun `returns popular movies when request succeeds`() = runTest {
        // Given
        val engine = MockEngine { request ->
            assertEquals(
                expected = "/movie/popular",
                actual = request.url.encodedPath,
            )

            respond(
                content = """
                {
                    "results": [
                        {
                            "id": 1,
                            "title": "Movie One"
                        },
                        {
                            "id": 2,
                            "title": "Movie Two"
                        }
                    ]
                }
            """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString(),
                ),
            )
        }

        createTestHttpClient(engine).use { client ->
            val remoteDataSource = KtorMoviesRemoteDataSource(httpClient = client)

            // When
            val result = remoteDataSource.getPopularMovies()

            // Then
            assertEquals(
                expected = Outcome.Success(
                    listOf(
                        Movie(
                            id = 1,
                            title = "Movie One",
                        ),
                        Movie(
                            id = 2,
                            title = "Movie Two",
                        ),
                    ),
                ),
                actual = result,
            )
        }
    }

    @Test
    fun `returns failure when request fails`() = runTest {
        // Given
        val engine = MockEngine { request ->
            assertEquals(
                expected = "/movie/popular",
                actual = request.url.encodedPath,
            )

            respond(
                content = "",
                status = HttpStatusCode.NotFound,
            )
        }

        createTestHttpClient(engine).use { client ->
            val remoteDataSource = KtorMoviesRemoteDataSource(httpClient = client)

            // When
            val result = remoteDataSource.getPopularMovies()

            // Then
            assertIs<Outcome.Failure<DomainError>>(
                value = result,
            )
        }
    }

    @Test
    fun `returns failure when response body is invalid`() = runTest {
        // Given
        val engine = MockEngine {
            respond(
                content = """
                {
                    "results": [
                        {
                            "id": "not-an-int",
                            "title": "Movie One"
                        }
                    ]
                }
            """.trimIndent(),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString(),
                ),
            )
        }

        createTestHttpClient(engine).use { client ->
            val remoteDataSource = KtorMoviesRemoteDataSource(httpClient = client)

            // When
            val result = remoteDataSource.getPopularMovies()

            // Then
            assertIs<Outcome.Failure<DomainError>>(
                value = result,
            )
        }
    }

    private fun createTestHttpClient(
        engine: MockEngine,
    ): HttpClient = HttpClient(engine) {
        val serverConfig = ServerConfig.create(
            baseUrl = "https://example.com",
            accessToken = "test-access-token",
        )

        configureNetworkClient(serverConfig = serverConfig)
    }
}
