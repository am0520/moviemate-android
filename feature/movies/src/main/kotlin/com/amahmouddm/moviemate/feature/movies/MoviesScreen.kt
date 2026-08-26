package com.amahmouddm.moviemate.feature.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        is MoviesUiState.Success -> Movies(movies = moviesUiState.movies)
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
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            Text(
                text = movie.title,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .padding(32.dp)
            )
        }
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(
        "Error",
        fontSize = 40.sp,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Text(
        "Loading",
        fontSize = 40.sp,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
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
