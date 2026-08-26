package com.amahmouddm.moviemate.data.movies

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.datasource.network.client.TmdbConfig
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(
        tmdbConfig: TmdbConfig,
    ): Outcome<List<Movie>, DomainError>
}
