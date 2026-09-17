package com.amahmouddm.moviemate.core.screenshottesting

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage

fun ComposeContentTestRule.captureScreenshot(
    component: String,
    state: String,
    variant: String,
) {
    onRoot().captureRoboImage(
        filePath = "$component/$state/$component+$state+$variant.png",
    )
}
