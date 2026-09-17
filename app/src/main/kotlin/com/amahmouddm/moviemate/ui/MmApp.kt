package com.amahmouddm.moviemate.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amahmouddm.moviemate.feature.movies.MoviesScreen

@Composable
internal fun MmApp(
    modifier: Modifier = Modifier
) {
    MoviesScreen(
        modifier = modifier
    )
}
