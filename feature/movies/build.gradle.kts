import java.util.Properties

plugins {
    alias(libs.plugins.moviemate.android.feature)
    alias(libs.plugins.screenshot)
}

val secrets = Properties().apply {
    val secretsFile = rootProject.file("secrets.properties")
    if (secretsFile.exists()) {
        secretsFile.inputStream().use { load(it) }
    }
}

android {
    namespace = "com.amahmouddm.moviemate.feature.movies"

    experimentalProperties["android.experimental.enableScreenshotTest"] = true

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

dependencies {
    implementation(projects.data.movies)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.mockk)
    testImplementation(testFixtures(projects.data.movies))
    testImplementation(projects.core.testing)

    screenshotTestImplementation(libs.screenshot.validation.api)
    screenshotTestImplementation(libs.androidx.compose.ui.tooling)
}
