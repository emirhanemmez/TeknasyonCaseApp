package module.feature.list

import com.android.build.api.dsl.LibraryExtension
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class PresentationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("feature")
            }

            extensions.configure<LibraryExtension> {
                namespace = "com.emirhanemmez.feature.list.presentation"
            }

            dependencies {
                implementation(project(":feature:list:domain"))
                implementation(library("hilt.navigation"))
            }
        }
    }
}