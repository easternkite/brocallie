import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.blucky8649.brocallie.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.multiplatformPlugin)
}

gradlePlugin {
    plugins {
        create("brocallie.compose.multiplatform.shared") {
            id = "brocallie.compose.multiplatform.shared"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        create("brocallie.android.application.compose") {
            id = "brocallie.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        create("brocallie.kotlin.multiplatform.shared") {
            id = "brocallie.kotlin.multiplatform.shared"
            implementationClass = "KotlinMultiplatformSharedConventionPlugin"
        }
    }
}

