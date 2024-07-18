package com.blucky8649.brocallie

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform() {
    extensions.configure<KotlinMultiplatformExtension> {
        sourceSets.apply {
            androidMain.dependencies {
                val navigationCompose = libs.findLibrary("androidx-navigation-compose").get()
                implementation(navigationCompose.get())
                val ktorAndroid = libs.findLibrary("ktor-client-android").get()
                implementation(ktorAndroid.get())
            }
            commonMain.dependencies {
                val compose = ComposePlugin.Dependencies(this@configureComposeMultiplatform)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                val viewModel = libs.findLibrary("kmp-viewmodel").get()
                implementation(viewModel)

                val coilCore = libs.findLibrary("coil-core").get()
                implementation(coilCore)
                val coilKtor = libs.findLibrary("coil-ktor").get()
                implementation(coilKtor)
                val coilCompose = libs.findLibrary("coil-compose").get()
                implementation(coilCompose)
                val coilComposeCore = libs.findLibrary("coil-compose-core").get()
                implementation(coilComposeCore)

            }
        }
    }
    extensions.configure<ComposeExtension>() {
        extensions.configure<ResourcesExtension> {
            publicResClass = true
            generateResClass = always
        }
    }
}