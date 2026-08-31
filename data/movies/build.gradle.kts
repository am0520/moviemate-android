plugins {
    alias(libs.plugins.moviemate.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moviemate.hilt)
    `java-test-fixtures`
}

dependencies {
    api(projects.core.common)

    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinxJson)

    testImplementation(libs.ktor.client.mock)
}
