import com.android.build.gradle.LibraryExtension
import com.emirhanemmez.convention.ext.androidTestImplementation
import com.emirhanemmez.convention.ext.configureAndroidCompose
import com.emirhanemmez.convention.ext.debugImplementation
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
            dependencies {
                implementation(library("androidx.core.ktx"))
                implementation(platform(library("androidx.compose.bom")))
                implementation(library("androidx.ui"))
                implementation(library("androidx.ui.graphics"))
                implementation(library("androidx.ui.tooling.preview"))
                implementation(library("androidx.material3"))
                androidTestImplementation(platform(library("androidx.compose.bom")))
                debugImplementation(library("androidx.ui.tooling"))
                debugImplementation(library("androidx.ui.test.manifest"))
            }
        }
    }
}