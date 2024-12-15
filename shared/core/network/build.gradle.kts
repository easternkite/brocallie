plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.logging)
        }
    }
}