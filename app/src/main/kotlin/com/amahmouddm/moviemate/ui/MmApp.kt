package com.amahmouddm.moviemate.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amahmouddm.moviemate.feature.movies.MoviesScreen

@Composable
internal fun MmApp(
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        MoviesScreen()
    }
}
