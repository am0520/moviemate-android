plugins {
    alias(libs.plugins.moviemate.kotlin.jvm)
}

dependencies {
    api(libs.kotlinx.coroutines.test)

    implementation(libs.junit)
}
