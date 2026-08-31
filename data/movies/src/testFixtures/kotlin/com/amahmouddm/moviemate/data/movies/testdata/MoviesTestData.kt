package com.amahmouddm.moviemate.data.movies.testdata

import com.amahmouddm.moviemate.data.movies.model.Movie

fun testMovie(
    id: Int = 1,
    title: String = "Movie 1",
) = Movie(
    id = id,
    title = title,
)

fun testMovies(
    vararg movies: Movie = arrayOf(
        testMovie(id = 1, title = "Movie 1"),
        testMovie(id = 2, title = "Movie 2"),
        testMovie(id = 3, title = "Movie 3"),
    )
) = movies.toList()
