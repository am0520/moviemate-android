import com.amahmouddm.moviemate.libs
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal abstract class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("moviemate.android.library")
            pluginManager.apply("moviemate.android.compose")
            pluginManager.apply("moviemate.hilt")

            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true

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
                        }
                    }
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodelCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.hilt.lifecycle.viewmodelCompose").get())

                "testImplementation"(libs.findLibrary("robolectric").get())
                "testImplementation"(libs.findLibrary("androidx.espresso").get())
            }
        }
    }
}
