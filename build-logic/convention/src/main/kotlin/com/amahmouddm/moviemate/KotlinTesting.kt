package com.amahmouddm.moviemate

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinTesting() {
    dependencies {
        "testImplementation"(libs.findLibrary("kotlin.test.junit").get())
        "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
    }
}
