
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.maven.publish)
}

allprojects {
    group = "dev.peopo"
}


val kotlinJvmPlugin = libs.plugins.kotlin.jvm
val mavenPublishPlugin = libs.plugins.maven.publish
val ktlintPlugin = libs.plugins.ktlint

subprojects {
    apply(plugin =  kotlinJvmPlugin.get().pluginId)
    apply(plugin =  mavenPublishPlugin.get().pluginId)
    apply(plugin =  ktlintPlugin.get().pluginId)

    tasks{
        withType<KotlinCompile>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-receivers")
            }
        }
        withType<Test> {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }

    publishing {
        publications {
            create<MavenPublication>(project.name) {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()

                from(components["java"])
            }
        }
    }
}