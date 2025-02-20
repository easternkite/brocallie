import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.brocallie.core.designsystem)
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.blucky8649.brocallie.shared.feature.conversation"

    defaultConfigs {
        val prompt = project.findProperty("MAKE_PERSONA") as String
        buildConfigField(FieldSpec.Type.STRING, "PROMPT_MAKE_PERSONA", prompt)
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.shared.core.generativeAi)
                api(projects.shared.core.room)
            }
        }
    }
}