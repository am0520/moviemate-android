plugins {
    alias(libs.plugins.moviemate.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.moviemate.hilt)
    `java-test-fixtures`
}

dependencies {
    api(projects.core.common)

    implementation(projects.core.network)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.ktor.client.mock)
}
