package module

import com.android.build.api.dsl.LibraryExtension
import com.emirhanemmez.convention.ext.androidTestImplementation
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import com.emirhanemmez.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("teknasyoncase.android.library.compose")
                apply("hilt")
            }

            dependencies {
                implementation(project(":common:presentation"))
                implementation(library("androidx.activity.compose"))
                implementation(library("androidx.lifecycle.runtime.ktx"))
                implementation(library("androidx.navigation.compose"))
                androidTestImplementation(library("androidx.junit"))
                androidTestImplementation(platform(library("androidx.compose.bom")))
                androidTestImplementation(library("androidx.ui.test.junit4"))
                testImplementation(library("kotest.junit5"))
                testImplementation(library("mockk"))
            }


            extensions.getByType<LibraryExtension>().apply {
                testOptions {
                    unitTests.all {
                        it.useJUnitPlatform()
                    }
                }

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }
}