package library

import com.emirhanemmez.convention.ext.implementation
import com.emirhanemmez.convention.ext.ksp
import com.emirhanemmez.convention.ext.library
import com.emirhanemmez.convention.ext.runtimeOnly
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                runtimeOnly(library("room.runtime"))
                ksp(library("room.compiler"))
                implementation(library("room.ktx"))
            }
        }
    }
}