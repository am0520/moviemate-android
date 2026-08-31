package com.amahmouddm.moviemate.feature.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.amahmouddm.moviemate.data.movies.model.Movie
import com.android.tools.screenshot.PreviewTest

class MoviesScreenVisualTest {

    @PreviewTest
    @Preview(
        name = "Movies - Success",
        showBackground = true,
    )
    @Composable
    fun MoviesScreenSuccessScreenshot() {
        MoviesScreen(
            moviesUiState = MoviesUiState.Success(
                movies = listOf(
                    Movie(id = 1, title = "Movie 1"),
                    Movie(
                        id = 2,
                        title = "Movie 2 - Additional Title",
                    ),
                    Movie(id = 3, title = "Movie 3"),
                    Movie(
                        id = 4,
                        title = "Movie 4 - Additional Title 1 - Additional Title 2",
                    ),
                    Movie(
                        id = 5,
                        title = "Movie 5 - This is a very long title to test " +
                                "the layout of the movie item in the grid.",
                    ),
                ),
            ),
        )
    }

    @PreviewTest
    @Preview(
        name = "Movies - Loading",
        showBackground = true,
    )
    @Composable
    fun MoviesScreenLoadingScreenshot() {
        MoviesScreen(
            moviesUiState = MoviesUiState.Loading,
        )
    }

    @PreviewTest
    @Preview(
        name = "Movies - Error",
        showBackground = true,
    )
    @Composable
    fun MoviesScreenErrorScreenshot() {
        MoviesScreen(
            moviesUiState = MoviesUiState.Error,
        )
    }
}
