plugins {
    alias(libs.plugins.moviemate.android.library)
}

android {
    namespace = "com.amahmouddm.moviemate.core.screenshottesting"
}

dependencies {
    implementation(libs.roborazzi)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.test)
}
