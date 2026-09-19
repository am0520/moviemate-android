plugins {
    alias(libs.plugins.moviemate.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moviemate.hilt)
    `java-test-fixtures`
}

dependencies {
    api(projects.core.common)

    implementation(projects.core.network)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinxJson)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.ktor.client.mock)
}
