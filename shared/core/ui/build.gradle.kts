plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.shared.core.room)
        implementation(projects.shared.feature.contacts)
        implementation(projects.shared.feature.conversation)
        implementation(projects.shared.feature.createcallie)
    }
}
