import com.amahmouddm.moviemate.libs
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal abstract class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val commonExtension = extensions.getByType<CommonExtension>()

            commonExtension.apply {
                buildFeatures.apply {
                    compose = true
                }
            }

            dependencies {
                "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
                "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

                "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
                "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())

                "testImplementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
                "testImplementation"(libs.findLibrary("androidx-compose-ui-test").get())
            }
        }
    }
}
