package com.amahmouddm.moviemate.core.screenshottesting

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage

fun ComposeContentTestRule.captureScreenshot(
    categories: List<String>,
    qualifiers: List<String> = emptyList(),
) {
    val fileNameSeparator = "+"
    val qualifiersSeparator = "-"
    val fileExtension = ".png"

    val folderPath = categories.joinToString("/")
    val fileSegments = if (qualifiers.isEmpty()){
        categories
    } else{
        categories + qualifiers.joinToString(qualifiersSeparator)
    }
    val fileName = fileSegments.joinToString(fileNameSeparator)

    val filePath = "$folderPath/$fileName$fileExtension"

    onRoot().captureRoboImage(
        filePath = filePath,
    )
}
