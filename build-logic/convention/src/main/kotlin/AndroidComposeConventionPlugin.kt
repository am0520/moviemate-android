import com.amahmouddm.moviemate.configureAndroidCompose
import com.amahmouddm.moviemate.configureComposeTests
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal abstract class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<CommonExtension>()

            configureAndroidCompose(extension)
            configureComposeTests()
        }
    }
}
