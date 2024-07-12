plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.shared.core.designsystem)
        }
    }
}