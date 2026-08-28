package com.amahmouddm.moviemate.feature.movies

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.core.testing.rule.MainDispatcherRule
import com.amahmouddm.moviemate.data.movies.MoviesRepository
import com.amahmouddm.moviemate.data.movies.testdata.domainErrorTestData
import com.amahmouddm.moviemate.data.movies.testdata.testMovies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val moviesRepository = mockk<MoviesRepository>()

    @Test
    fun `initial state is loading`() = runTest {
        coEvery {
            moviesRepository.getPopularMovies(any())
        } returns Outcome.Success(emptyList())

        val viewModel = MoviesViewModel(
            moviesRepository = moviesRepository,
        )

        assertEquals(
            MoviesUiState.Loading,
            viewModel.moviesUiState,
        )
    }

    @Test
    fun `when repository returns success state is success`() = runTest {
        val movies = testMovies()

        coEvery {
            moviesRepository.getPopularMovies(any())
        } returns Outcome.Success(movies)

        val viewModel = MoviesViewModel(
            moviesRepository = moviesRepository,
        )

        advanceUntilIdle()

        assertEquals(
            MoviesUiState.Success(movies = movies),
            viewModel.moviesUiState,
        )
    }

    @Test
    fun `when repository returns failure state is error`() = runTest {
        val domainError = domainErrorTestData.random()

        coEvery {
            moviesRepository.getPopularMovies(any())
        } returns Outcome.Failure(domainError)

        val viewModel = MoviesViewModel(
            moviesRepository = moviesRepository,
        )

        advanceUntilIdle()

        assertEquals(
            MoviesUiState.Error,
            viewModel.moviesUiState,
        )
    }
}
