plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.google.services)
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "Firebase Storage for KMP Integration"
        homepage = "https://github.com/blucky8649/brocallie"

        ios.deploymentTarget = "13.5"

        pod("FirebaseStorage"){
            moduleName = "FirebaseStorage"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.storage.ktx)
}