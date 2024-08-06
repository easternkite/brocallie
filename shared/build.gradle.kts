import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.brocallie.core.designsystem)
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
            export(projects.shared.feature.conversation)
            export(projects.shared.feature.createcallie)
            export(projects.shared.core.firebase)
            linkerOpts("-framework", "FirebaseStorage")
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(projects.shared.feature.contacts)
            api(projects.shared.feature.contactdetails)
            api(projects.shared.feature.conversation)
            api(projects.shared.feature.createcallie)
            api(projects.shared.core.firebase)
        }
    }
}