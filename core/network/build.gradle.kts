plugins {
    alias(libs.plugins.moviemate.kotlin.jvm)
}

dependencies {
    api(platform(libs.ktor.bom))
    api(libs.ktor.client.core)
    api(projects.core.common)

    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinxJson)
    implementation(libs.kotlinx.serialization.json)
}
