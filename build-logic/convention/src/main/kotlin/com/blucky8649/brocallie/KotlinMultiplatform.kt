package com.blucky8649.brocallie

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform() {

    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }

        iosX64()
        iosArm64()
        iosSimulatorArm64()

        task("testClasses")

        sourceSets.apply {
            commonMain.dependencies {
                val datetime = libs.findLibrary("kotlinx-datetime").get()
                implementation(datetime)
            }

            commonTest.dependencies {
                val test = libs.findLibrary("kotlin-test").get()
                implementation(test)
            }
        }
    }

    extensions.configure<LibraryExtension> {
        namespace = "com.blucky8649.brocallie"
        compileSdk = 34
        defaultConfig {
            minSdk = 24
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}