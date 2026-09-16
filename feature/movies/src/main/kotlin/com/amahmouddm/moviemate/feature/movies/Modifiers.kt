package com.amahmouddm.moviemate.feature.movies

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import kotlin.math.roundToInt

internal fun Modifier.heightMinOfAspectRatioAndFixed(
    aspectRatio: Float,
    fixedHeight: Dp,
) = layout { measurable, constraints ->
    require(constraints.hasBoundedWidth) {
        "heightMinOfAspectRatioAndFixed modifier requires a bounded width " +
                "to calculate the height based on the aspect ratio."
    }

    val constrainedAspectRatioHeight = constraints.constrainHeight(
        (constraints.maxWidth / aspectRatio).roundToInt()
    )
    val constrainedFixedHeight = constraints.constrainHeight(
        fixedHeight.roundToPx()
    )

    val height = minOf(constrainedAspectRatioHeight, constrainedFixedHeight)

    val placeable = measurable.measure(
        constraints.copy(
            minHeight = height,
            maxHeight = height,
        )
    )

    layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
    }
}
