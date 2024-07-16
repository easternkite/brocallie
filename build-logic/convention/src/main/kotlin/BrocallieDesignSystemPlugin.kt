import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class BrocallieDesignSystemPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain {
                dependencies {
                    implementation(project(":shared:core:designsystem"))
                }
            }
        }
    }
}