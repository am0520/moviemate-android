plugins {
    alias(libs.plugins.moviemate.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.amahmouddm.moviemate.core.networktest"
}

dependencies {
    api(platform(libs.ktor.bom))
    api(libs.ktor.client.mock)

    implementation(projects.core.network)
    implementation(libs.hilt.android.testing)
    ksp(libs.hilt.compiler)
}
