plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
}

kotlin {
    sourceSets {
        androidMain.dependencies {

        }

        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.kmp.viewmodel)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
