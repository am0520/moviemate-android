import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.amahmouddm.moviemate.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.ksp.plugin)
    compileOnly(libs.android.gradleApi)
    compileOnly(libs.kotlin.compose.plugin)
    compileOnly(libs.roborazzi.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = libs.plugins.moviemate.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.moviemate.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.moviemate.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinJvm") {
            id = libs.plugins.moviemate.kotlin.jvm.get().pluginId
            implementationClass = "KotlinJvmConventionPlugin"
        }
        register("androidCompose") {
            id = libs.plugins.moviemate.android.compose.get().pluginId
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.moviemate.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
