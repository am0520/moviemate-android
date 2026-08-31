package com.amahmouddm.moviemate.feature.movies

import com.amahmouddm.moviemate.data.movies.model.Movie

internal sealed interface MoviesUiState {
    data object Loading : MoviesUiState
    data object Error : MoviesUiState
    data class Success(val movies: List<Movie>) : MoviesUiState
}
