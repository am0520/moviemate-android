package com.amahmouddm.moviemate.data.movies.datasource.network.mapper

import com.amahmouddm.moviemate.data.movies.datasource.network.dto.MovieDto
import com.amahmouddm.moviemate.data.movies.model.Movie

internal fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
    )
}
