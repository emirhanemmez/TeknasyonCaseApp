package module.feature.detail

import com.emirhanemmez.convention.ext.configureKotlinJvm
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import com.emirhanemmez.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DomainPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            configureKotlinJvm()

            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
            }

            dependencies {
                implementation(library("javax.inject"))
                implementation(library("coroutines.test"))
                testImplementation(library("mockk"))
                testImplementation(library("kotest.junit5"))
            }
        }
    }
}