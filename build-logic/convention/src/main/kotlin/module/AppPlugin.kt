package module

import com.android.build.api.dsl.ApplicationExtension
import com.emirhanemmez.convention.ext.androidTestImplementation
import com.emirhanemmez.convention.ext.debugImplementation
import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("teknasyoncase.android.application.compose")
                apply("hilt")
            }

            dependencies {
                implementation(project(":common:data"))
                implementation(project(":common:presentation"))
                implementation(project(":feature:list:data"))
                implementation(project(":feature:list:domain"))
                implementation(project(":feature:list:presentation"))
                implementation(project(":feature:detail:data"))
                implementation(project(":feature:detail:domain"))
                implementation(project(":feature:detail:presentation"))
                implementation(library("androidx.core.ktx"))
                implementation(library("androidx.lifecycle.runtime.ktx"))
                implementation(library("androidx.activity.compose"))
                implementation(platform(library("androidx.compose.bom")))
                implementation(library("androidx.ui"))
                implementation(library("androidx.ui.graphics"))
                implementation(library("androidx.ui.tooling.preview"))
                implementation(library("androidx.material3"))
                implementation(library("androidx.navigation.compose"))
                androidTestImplementation(library("androidx.junit"))
                androidTestImplementation(library("androidx.espresso.core"))
                androidTestImplementation(platform(library("androidx.compose.bom")))
                androidTestImplementation(library("androidx.ui.test.junit4"))
                androidTestImplementation(library("androidx.navigation.testing"))
                androidTestImplementation(library("hilt.navigation"))
                debugImplementation(library("androidx.ui.tooling"))
                debugImplementation(library("androidx.ui.test.manifest"))
            }

            extensions.getByType<ApplicationExtension>().apply {
                namespace = "com.emirhanemmez.teknasyoncase"

                defaultConfig {
                    applicationId = "com.emirhanemmez.teknasyoncase"

                    testInstrumentationRunner = "com.emirhanemmez.teknasyoncase.HiltTestRunner"
                }
            }
        }
    }
}