import com.amahmouddm.moviemate.configureKotlinJvm
import com.amahmouddm.moviemate.configureKotlinTesting
import org.gradle.api.Plugin
import org.gradle.api.Project

internal abstract class KotlinJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            configureKotlinJvm()
            configureKotlinTesting()
        }
    }
}
