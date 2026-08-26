package com.amahmouddm.moviemate.feature.movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val movie = viewModel.movie

    Text(
        movie,
        fontSize = 40.sp,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}
