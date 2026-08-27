package com.amahmouddm.moviemate.data.movies

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.data.moviesTestData
import com.amahmouddm.moviemate.data.movies.datasource.network.client.ServerConfig
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.testdoubles.FakeMoviesRemoteDataSource
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultMoviesRepositoryTest {

    private val movies = moviesTestData

    private val remoteDataSource = FakeMoviesRemoteDataSource()

    private val repository = DefaultMoviesRepository(
        remoteDataSource = remoteDataSource,
    )

    private val serverConfig = ServerConfig.create(
        baseUrl = "",
        accessToken = "",
    )

    @Test
    fun `returns movies when remote data source succeeds`() = runTest {
        // Given
        remoteDataSource.result = Outcome.Success(movies)

        // When
        val result =
            repository.getPopularMovies(serverConfig = serverConfig)

        // Then
        assertEquals(
            expected = Outcome.Success(movies),
            actual = result,
        )
    }

    @Test
    fun `returns error when remote data source fails`() = runTest {
        // Given
        remoteDataSource.result = Outcome.Failure(DomainError.CONNECTION_FAILED)

        // When
        val result =
            repository.getPopularMovies(serverConfig = serverConfig)

        // Then
        assertEquals(
            expected = Outcome.Failure(DomainError.CONNECTION_FAILED),
            actual = result,
        )
    }
}
