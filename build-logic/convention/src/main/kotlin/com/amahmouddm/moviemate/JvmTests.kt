package com.amahmouddm.moviemate

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureJvmTests() {
    dependencies {
        "testImplementation"(libs.findLibrary("kotlin.test").get())
    }
}
