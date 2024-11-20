package module.common

import com.android.build.api.dsl.LibraryExtension
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class PresentationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("teknasyoncase.android.library.compose")
            }

            dependencies {
                implementation(library("androidx.lifecycle.runtime.ktx"))
                implementation(library("androidx.navigation.common.ktx"))
                implementation(library("androidx.material.icons"))
            }

            extensions.getByType<LibraryExtension>().apply {
                namespace = "com.emirhanemmez.common.presentation"
            }
        }
    }
}