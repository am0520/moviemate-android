import com.amahmouddm.moviemate.configureAndroidFeature
import com.amahmouddm.moviemate.configureKotlinAndroid
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal abstract class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = 37
                configureKotlinAndroid(this)
                configureAndroidFeature(this)
            }
        }
    }
}
