plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(libs.androidx.room.runtime)
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    ksp(libs.androidx.room.compiler)
}