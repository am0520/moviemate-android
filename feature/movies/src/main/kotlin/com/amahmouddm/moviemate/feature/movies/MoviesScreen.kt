package com.amahmouddm.moviemate.feature.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.amahmouddm.moviemate.data.movies.model.Movie

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val moviesUiState = viewModel.moviesUiState

    MoviesScreen(
        moviesUiState = moviesUiState,
        modifier = modifier
    )
}

@Composable
internal fun MoviesScreen(
    moviesUiState: MoviesUiState,
    modifier: Modifier = Modifier
) {
    when (moviesUiState) {
        is MoviesUiState.Success -> {
            MoviesList(movies = moviesUiState.movies, modifier = modifier.fillMaxSize())
        }

        is MoviesUiState.Error -> Error(modifier = modifier.fillMaxSize())

        is MoviesUiState.Loading -> Loading(modifier = modifier.fillMaxSize())
    }
}

@Composable
private fun MoviesList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val contentPadding = WindowInsets.safeContent.asPaddingValues()
        val availableHeight = maxOf(
            0.dp,
            maxHeight -
                    contentPadding.calculateTopPadding() -
                    contentPadding.calculateBottomPadding()
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        ) {
            items(
                items = movies,
                key = { movie -> movie.id },
            ) { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightMinOfAspectRatioAndFixed(2f / 3f, availableHeight)
                )
            }
        }
    }
}

@Composable
private fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Text(
        text = movie.title,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .background(Color.DarkGray)
            .wrapContentSize()
    )
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(
        "Error",
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .wrapContentSize()
    )
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

@Preview(device = Devices.PHONE)
@Composable
private fun MoviesScreenPreview() {
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

    MoviesScreen(moviesUiState = moviesUiState)
}
