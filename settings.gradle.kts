enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BroCallie"
include(":androidApp")

include(":shared:feature:contacts")
include(":shared:feature:profile")
include(":shared:feature:createcallie")
include(":shared:feature:conversation")

include(":shared:core:generative-ai")
include(":shared:core:designsystem")
include(":shared:core:room")
include(":shared:core:firebase")
include(":shared:core:ui")
include(":shared:core:network")
