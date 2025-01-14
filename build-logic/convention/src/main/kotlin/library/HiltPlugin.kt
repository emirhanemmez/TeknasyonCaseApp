package library

import com.emirhanemmez.convention.ext.androidTestImplementation
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.ksp
import com.emirhanemmez.convention.ext.kspAndroidTest
import com.emirhanemmez.convention.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                implementation(library("hilt.android"))
                ksp(library("hilt.compiler"))
                implementation(library("hilt.navigation"))
                androidTestImplementation(library("hilt.android"))
                androidTestImplementation(library("hilt.testing"))
                androidTestImplementation(library("hilt.navigation"))
                kspAndroidTest(library("hilt.compiler"))
            }
        }
    }
}