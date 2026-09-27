package com.amahmouddm.moviemate

import com.android.build.api.dsl.CommonExtension
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke

internal fun Project.configureAndroidFeature(
    commonExtension: CommonExtension,
) {
    pluginManager.apply("moviemate.android.compose")
    pluginManager.apply("moviemate.hilt")
    pluginManager.apply("io.github.takahirom.roborazzi")

    commonExtension.apply {
        testOptions.unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true

            all {
                it.jvmArgs(
                    "--add-opens=java.base/java.lang=ALL-UNNAMED",
                    "--add-opens=java.base/java.util=ALL-UNNAMED",
                    "--add-opens=java.base/java.io=ALL-UNNAMED",
                    "--add-opens=java.base/java.net=ALL-UNNAMED",
                    "--add-opens=java.base/java.security=ALL-UNNAMED",
                    "--add-opens=java.base/java.text=ALL-UNNAMED",
                    "--add-opens=java.base/jdk.internal.access=ALL-UNNAMED",
                    "--add-opens=java.desktop/java.awt.font=ALL-UNNAMED",
                    "--add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
                )

                it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
            }

        }

        sourceSets {
            named("test") {
                resources.directories += rootProject.file("config/robolectric").path
            }
        }
    }

    extensions.configure<RoborazziExtension> {
        outputDir.set(layout.projectDirectory.dir("src/test/screenshots"))

        @OptIn(ExperimentalRoborazziApi::class)
        separateOutputDirs.set(true)
    }

    tasks.named("check") {
        dependsOn("verifyRoborazziDebug")
    }

    dependencies {
        "testImplementation"(libs.findLibrary("robolectric").get())
        "testImplementation"(libs.findLibrary("androidx.espresso").get())
        "testImplementation"(project(":core:screenshot-testing"))
    }
}
