package com.amahmouddm.moviemate.feature.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.MoviesRepository
import com.amahmouddm.moviemate.data.movies.datasource.network.client.TmdbConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TMDB_BASE_URL = BuildConfig.TMDB_BASE_URL
private const val TMDB_ACCESS_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    var movie by mutableStateOf("Loading")
    val tmdbConfig = TmdbConfig.create(
        baseUrl = TMDB_BASE_URL,
        accessToken = TMDB_ACCESS_TOKEN,
    )

    init {
        viewModelScope.launch {
            val movies = moviesRepository.getPopularMovies(
                tmdbConfig = tmdbConfig,
            )
            movie = when (movies) {
                is Outcome.Success -> movies.data.first().title
                is Outcome.Failure -> "Error"
            }
        }
    }

}
