import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.blucky8649.brocallie.shared.generative-ai"

    // default config is required
    defaultConfigs {
        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("GEMINI_KEY")
        buildConfigField(FieldSpec.Type.STRING, "API_KEY", apiKey)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.generativeAi)
        }
    }
}


