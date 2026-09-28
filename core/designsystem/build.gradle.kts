plugins {
    alias(libs.plugins.moviemate.android.library)
    alias(libs.plugins.moviemate.android.compose)
}

android {
    namespace = "com.amahmouddm.moviemate.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material3)

    implementation(libs.androidx.compose.ui.text.googleFonts)
}
