package com.emirhanemmez.convention.ext

import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

internal fun DependencyHandlerDelegate.implementation(dependency: Any) {
    add("implementation", dependency)
}

internal fun DependencyHandlerDelegate.api(dependency: Any) {
    add("api", dependency)
}

internal fun DependencyHandlerDelegate.runtimeOnly(dependency: Any) {
    add("runtimeOnly", dependency)
}

internal fun DependencyHandlerDelegate.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

internal fun DependencyHandlerDelegate.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandlerDelegate.kapt(dependency: Any) {
    add("kapt", dependency)
}

internal fun DependencyHandlerDelegate.kaptAndroidTest(dependency: Any) {
    add("kaptAndroidTest", dependency)
}

internal fun DependencyHandlerDelegate.ksp(dependency: Any) {
    add("ksp", dependency)
}

internal fun DependencyHandlerDelegate.kspAndroidTest(dependency: Any) {
    add("kspAndroidTest", dependency)
}

internal fun DependencyHandlerDelegate.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}