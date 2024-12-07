plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.android.dynamic.feature).apply(false)
    alias(libs.plugins.buildKonfig).apply(false)
    alias(libs.plugins.google.services).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.ksp).apply(false)
}

tasks.register("updateInfoPlist") {
    val infoPlistFile = file("iosApp/iosApp/Info.plist")
    val targetVersionName = libs.versions.versionName.get()
    val targetVersionCode = libs.versions.versionCode.get()

    providers.exec {
        commandLine("plutil", "-replace", "CFBundleShortVersionString", "-string", targetVersionName, infoPlistFile.absolutePath)
    }.result.get()
    providers.exec {
        commandLine("plutil", "-replace", "CFBundleVersion", "-string", targetVersionCode, infoPlistFile.absolutePath)
    }.result.get()
}