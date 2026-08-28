package com.amahmouddm.moviemate.data.movies.testdoubles

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.datasource.MoviesRemoteDataSource
import com.amahmouddm.moviemate.data.movies.datasource.network.client.ServerConfig
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie

internal class FakeMoviesRemoteDataSource : MoviesRemoteDataSource {

    lateinit var result: Outcome<List<Movie>, DomainError>

    var callCount = 0
        private set

    override suspend fun getPopularMovies(
        serverConfig: ServerConfig,
    ): Outcome<List<Movie>, DomainError> {
        callCount++
        return result
    }
}
