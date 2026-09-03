package com.amahmouddm.moviemate.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amahmouddm.moviemate.data.movies.model.Movie
import com.amahmouddm.moviemate.feature.movies.MoviesScreen
import com.amahmouddm.moviemate.feature.movies.MoviesUiState

@Composable
internal fun MmApp(
    modifier: Modifier = Modifier
) {
    MoviesScreen(
        modifier = modifier
    )
}

@Composable
internal fun MmApp(
    moviesUiState: MoviesUiState,
    modifier: Modifier = Modifier
) {
    MoviesScreen(
        moviesUiState = moviesUiState,
        modifier = modifier
    )
}

@Preview
@Composable
private fun MmAppPreview() {
    val moviesUiState = MoviesUiState.Success(
        movies = listOf(
            Movie(id = 1, title = "Movie 1"),
            Movie(id = 2, title = "Movie 2 - Additional Title"),
            Movie(id = 3, title = "Movie 3"),
            Movie(id = 4, title = "Movie 4 - Additional Title 1 - Additional Title 2"),
            Movie(
                id = 5,
                title = "Movie 5 - This is a very long title to test the layout of the " +
                        "movie item in the grid. Lorem ipsum dolor sit amet, consectetur " +
                        "adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore ",
            ),
        )
    )

    MmApp(moviesUiState = moviesUiState)
}
