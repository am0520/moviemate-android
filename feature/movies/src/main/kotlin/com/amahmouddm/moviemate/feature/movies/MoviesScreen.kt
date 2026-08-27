package com.amahmouddm.moviemate.feature.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
        is MoviesUiState.Success -> Movies(movies = moviesUiState.movies, modifier = modifier)
        is MoviesUiState.Error -> Error(modifier = modifier)
        is MoviesUiState.Loading -> Loading(modifier = modifier)
    }
}

@Composable
private fun Movies(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = WindowInsets.safeContent.asPaddingValues(),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        modifier = modifier
    ) {
        items(
            items = movies,
            key = { movie -> movie.id },
        ) { movie ->
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Green)
                    .wrapContentSize()
            )
        }
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(
        "Error",
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun MoviesScreenPreview() {
    MoviesScreen(
        moviesUiState = MoviesUiState.Success(
            movies = listOf(
                Movie(id = 1, title = "Movie 1"),
                Movie(id = 2, title = "Movie 2"),
                Movie(id = 3, title = "Movie 3"),
                Movie(id = 4, title = "Movie 4"),
            )
        )
    )
}
