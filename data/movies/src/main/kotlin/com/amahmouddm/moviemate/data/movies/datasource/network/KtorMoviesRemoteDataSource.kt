package com.amahmouddm.moviemate.data.movies.datasource.network

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.core.common.result.mapSuccess
import com.amahmouddm.moviemate.data.movies.datasource.MoviesRemoteDataSource
import com.amahmouddm.moviemate.data.movies.datasource.network.dto.PopularMoviesResponseDto
import com.amahmouddm.moviemate.data.movies.datasource.network.mapper.toMovie
import com.amahmouddm.moviemate.data.movies.datasource.network.result.safeApiCall
import com.amahmouddm.moviemate.data.movies.model.DomainError
import com.amahmouddm.moviemate.data.movies.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class KtorMoviesRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient,
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(): Outcome<List<Movie>, DomainError> {
        return safeApiCall {
            httpClient
                .get("movie/popular")
                .body<PopularMoviesResponseDto>()
        }
            .mapSuccess { data ->
                data
                    .results
                    .map { it.toMovie() }
            }
    }
}
