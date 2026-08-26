package com.amahmouddm.moviemate.data.movies

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.datasource.MoviesRemoteDataSource
import com.amahmouddm.moviemate.data.movies.datasource.network.client.TmdbConfig
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultMoviesRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
) : MoviesRepository {

    override suspend fun getPopularMovies(
        tmdbConfig: TmdbConfig,
        ): Outcome<List<Movie>, DomainError> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getPopularMovies(
                tmdbConfig = tmdbConfig,
            )
        }
}
