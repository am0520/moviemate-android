import com.amahmouddm.moviemate.configureKotlinAndroid
import com.amahmouddm.moviemate.configureKotlinTesting
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal abstract class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                testOptions.targetSdk = 37
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                resourcePrefix = path.removePrefix(":").replace(":", "_").lowercase() + "_"
            }

            configureKotlinTesting()
        }
    }
}
