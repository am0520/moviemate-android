package com.amahmouddm.moviemate.data.movies.datasource.network.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponseDto(
    val results: List<MovieDto>,
)

@Serializable
internal data class MovieDto(
    val id: Int,
    val title: String,
)
