plugins {
    alias(libs.plugins.moviemate.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.amahmouddm.moviemate.core.datatest"
}

dependencies {
    api(libs.hilt.android.testing)

    implementation(projects.data.movies)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.mock)
    implementation(projects.core.network)
    ksp(libs.hilt.compiler)
}
