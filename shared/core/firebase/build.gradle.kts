import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.google.gson.JsonParser

plugins {
    alias(libs.plugins.brocallie.kotlin.multiplatform.shared)
    alias(libs.plugins.brocallie.compose.multiplatform.shared)
    alias(libs.plugins.google.services)
    alias(libs.plugins.serialization)
    alias(libs.plugins.buildKonfig)
}

buildkonfig {
    packageName = "com.blucky8649.brocallie.shared.core.firebase"

    // default config is required
    defaultConfigs {
        val storageBucket = getStorageBucketFromGoogleServiceConfig()
        buildConfigField(FieldSpec.Type.STRING, "STORAGE_BUCKET", storageBucket)
    }
}

kotlin {
    sourceSets {
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(projects.shared.core.network)
        }
        androidMain.dependencies {
            implementation(projects.shared.core.network)
        }
    }
}

fun getStorageBucketFromGoogleServiceConfig() = runCatching {
    val googleService = File("${rootProject.rootDir}/androidApp/google-services.json").readText()
    val json = JsonParser.parseString(googleService).asJsonObject
    val projectInfo = json.getAsJsonObject("project_info")
    val storageBucket = projectInfo.get("storage_bucket").asString
    storageBucket
}.getOrNull() ?: ""

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.storage.ktx)
}