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
            export(projects.shared.core.ui)
            export(projects.shared.core.firebase)
            linkerOpts("-framework", "FirebaseStorage")
            linkerOpts.add("-lsqlite3")
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(projects.shared.core.ui)
            api(projects.shared.core.firebase)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.macos_arm64)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.easternkite.eungabi.MainKt"
    }
}