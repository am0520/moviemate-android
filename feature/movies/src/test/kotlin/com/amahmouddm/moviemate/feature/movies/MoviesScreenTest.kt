package com.amahmouddm.moviemate.feature.movies

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amahmouddm.moviemate.data.movies.testdata.testMovie
import com.amahmouddm.moviemate.data.movies.testdata.testMovies
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `loading state displays progress indicator`() {
        composeTestRule.setContent {
            MoviesScreen(
                moviesUiState = MoviesUiState.Loading,
            )
        }

        composeTestRule
            .onNode(
                hasProgressBarRangeInfo(
                    ProgressBarRangeInfo.Indeterminate
                )
            )
            .assertIsDisplayed()
    }

    @Test
    fun `error state displays error`() {
        composeTestRule.setContent {
            MoviesScreen(
                moviesUiState = MoviesUiState.Error,
            )
        }

        composeTestRule
            .onNodeWithText("Error")
            .assertIsDisplayed()
    }

    @Test
    fun `success state displays movies`() {
        val movies = testMovies(
            testMovie(id = 1, title = "Movie 1"),
            testMovie(id = 2, title = "Movie 2"),
        )

        composeTestRule.setContent {
            MoviesScreen(
                moviesUiState = MoviesUiState.Success(
                    movies = movies
                ),
            )
        }

        composeTestRule
            .onNodeWithText("Movie 1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Movie 2")
            .assertIsDisplayed()
    }
}
