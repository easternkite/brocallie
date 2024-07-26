plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.brocallie.core.designsystem)
}
kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.peekaboo.ui)
        implementation(libs.peekaboo.image.picker)
    }
}