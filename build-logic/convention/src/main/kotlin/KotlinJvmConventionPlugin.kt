import com.amahmouddm.moviemate.configureKotlinTests
import com.amahmouddm.moviemate.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

internal abstract class KotlinJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")

            configureKotlinJvm()
            configureKotlinTests()
        }
    }
}
