import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.emirhanemmez.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "teknasyoncase.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "teknasyoncase.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "teknasyoncase.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("hilt") {
            id = "hilt"
            implementationClass = "library.HiltPlugin"
        }
        register("room") {
            id = "room"
            implementationClass = "library.RoomPlugin"
        }
        register("app") {
            id = "app"
            implementationClass = "module.AppPlugin"
        }
        register("navigation") {
            id = "navigation"
            implementationClass = "module.NavigationPlugin"
        }
        register("commonDataModule") {
            id = "common.data"
            implementationClass = "module.common.DataPlugin"
        }
        register("commonPresentationModule") {
            id = "common.presentation"
            implementationClass = "module.common.PresentationPlugin"
        }
        register("feature") {
            id = "feature"
            implementationClass = "module.FeaturePlugin"
        }
        register("featureListDataModule") {
            id = "feature.list.data"
            implementationClass = "module.feature.list.DataPlugin"
        }
        register("featureListDomainModule") {
            id = "feature.list.domain"
            implementationClass = "module.feature.list.DomainPlugin"
        }
        register("featureListPresentationModule") {
            id = "feature.list.presentation"
            implementationClass = "module.feature.list.PresentationPlugin"
        }
        register("featureDetailDataModule") {
            id = "feature.detail.data"
            implementationClass = "module.feature.detail.DataPlugin"
        }
        register("featureDetailDomainModule") {
            id = "feature.detail.domain"
            implementationClass = "module.feature.detail.DomainPlugin"
        }
        register("featureDetailPresentationModule") {
            id = "feature.detail.presentation"
            implementationClass = "module.feature.detail.PresentationPlugin"
        }
    }
}