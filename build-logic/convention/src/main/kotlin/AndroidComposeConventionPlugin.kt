import com.amahmouddm.moviemate.configureAndroidCompose
import com.amahmouddm.moviemate.configureComposeTests
import com.amahmouddm.moviemate.libs
import com.android.build.api.dsl.CommonExtension
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import io.github.takahirom.roborazzi.RoborazziExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke

internal abstract class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            pluginManager.apply("io.github.takahirom.roborazzi")

            extensions.configure<CommonExtension> {
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

                    sourceSets {
                        named("test") {
                            resources.directories += rootProject.file("config/robolectric").path
                        }
                    }
                }
            }

            extensions.configure<RoborazziExtension> {
                outputDir.set(layout.projectDirectory.dir("src/test/screenshots"))
                @OptIn(ExperimentalRoborazziApi::class)
                separateOutputDirs.set(true)
            }

            val extension = extensions.getByType<CommonExtension>()

            configureAndroidCompose(extension)
            configureComposeTests()

            dependencies {
                "testImplementation"(libs.findLibrary("robolectric").get())
                "testImplementation"(libs.findLibrary("androidx.espresso").get())
                "testImplementation"(libs.findLibrary("roborazzi").get())
                "testImplementation"(libs.findLibrary("roborazzi.compose").get())
            }
        }
    }
}
