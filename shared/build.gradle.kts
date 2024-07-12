import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(projects.shared.feature.contacts)
            export(projects.shared.feature.contactdetails)
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(projects.shared.core.designsystem)
            api(projects.shared.feature.contacts)
            api(projects.shared.feature.contactdetails)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}