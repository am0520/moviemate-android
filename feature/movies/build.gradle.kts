@file:OptIn(ExperimentalRoborazziApi::class)

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import java.util.Properties

plugins {
    alias(libs.plugins.moviemate.android.feature)
    id("io.github.takahirom.roborazzi")
}

val secrets = Properties().apply {
    val secretsFile = rootProject.file("secrets.properties")
    if (secretsFile.exists()) {
        secretsFile.inputStream().use { load(it) }
    }
}

android {
    namespace = "com.amahmouddm.moviemate.feature.movies"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            all {
                it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            val tmdbAccessToken = secrets.getProperty("TMDB_ACCESS_TOKEN", "")
            val tmdbBaseUrl = secrets.getProperty("TMDB_BASE_URL", "")

            buildConfigField(
                "String",
                "TMDB_ACCESS_TOKEN",
                "\"$tmdbAccessToken\""
            )
            buildConfigField(
                "String",
                "TMDB_BASE_URL",
                "\"$tmdbBaseUrl\""
            )
        }

        release {
            buildConfigField(
                "String",
                "TMDB_ACCESS_TOKEN",
                "\"\""
            )
            buildConfigField(
                "String",
                "TMDB_BASE_URL",
                "\"\""
            )
        }
    }
}

roborazzi {
    outputDir = file("src/test/screenshots")
    separateOutputDirs = true
}

dependencies {
    api(projects.data.movies)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.mockk)
    testImplementation(testFixtures(projects.data.movies))
    testImplementation(projects.core.testing)

    testImplementation("io.github.takahirom.roborazzi:roborazzi:1.73.0")
    testImplementation("io.github.takahirom.roborazzi:roborazzi-compose:1.73.0")
}
