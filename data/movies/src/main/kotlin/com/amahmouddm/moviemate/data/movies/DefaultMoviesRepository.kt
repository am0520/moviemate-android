package com.amahmouddm.moviemate.data.movies

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.datasource.MoviesRemoteDataSource
import com.amahmouddm.moviemate.data.movies.datasource.network.client.ServerConfig
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie
import javax.inject.Inject

internal class DefaultMoviesRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
) : MoviesRepository {

    override suspend fun getPopularMovies(
        serverConfig: ServerConfig,
    ): Outcome<List<Movie>, DomainError> {
        val moviesOutcome = remoteDataSource.getPopularMovies(
            serverConfig = serverConfig,
        )

        return moviesOutcome
    }
}
