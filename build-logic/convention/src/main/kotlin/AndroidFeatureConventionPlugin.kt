import com.amahmouddm.moviemate.configureAndroidFeature
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

            extensions.configure<LibraryExtension> {
                configureAndroidFeature(this)
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodelCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.hilt.lifecycle.viewmodelCompose").get())
            }
        }
    }
}
