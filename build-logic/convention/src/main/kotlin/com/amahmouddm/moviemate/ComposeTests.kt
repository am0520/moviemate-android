package com.amahmouddm.moviemate

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureComposeTests() {
    dependencies {
        "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())

        "testImplementation"(libs.findLibrary("androidx-compose-ui-test").get())
    }
}
