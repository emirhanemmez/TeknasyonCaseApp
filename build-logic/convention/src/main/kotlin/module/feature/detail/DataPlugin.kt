package module.feature.detail

import com.android.build.api.dsl.LibraryExtension
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import com.emirhanemmez.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DataPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("teknasyoncase.android.library")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("hilt")
                apply("room")
            }

            extensions.getByType<LibraryExtension>().apply {
                namespace = "com.emirhanemmez.feature.detail.data"

                testOptions {
                    unitTests.all {
                        it.useJUnitPlatform()
                    }
                }
            }

            dependencies {
                implementation(project(":common:data"))
                implementation(project(":feature:detail:domain"))
                implementation(library("androidx.core.ktx"))
                implementation(library("kotlin.serialization"))
                testImplementation(library("coroutines.test"))
                testImplementation(library("mockk"))
                testImplementation(library("kotest.junit5"))
            }
        }
    }
}