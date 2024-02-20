package dev.peopo.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

public class BuildLogicPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            configureKotlin()
            configureTests()
        }
    }


    private fun Project.configureKotlin() {
        tasks.named("compileKotlin", KotlinCompilationTask::class.java) {
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-receivers")
            }
        }
        extensions.configure<KotlinJvmProjectExtension> {
            jvmToolchain(21)
            explicitApi()
        }
    }

    private fun Project.configureTests() {
        tasks.withType<Test> {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }

    private fun Project.applyPlugins() {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        val kotlinJvmPlugin = libs.findPlugin("kotlin-jvm").get().get()
        val ktlintPlugin = libs.findPlugin("ktlint").get().get()

        pluginManager.apply(kotlinJvmPlugin.pluginId)
        pluginManager.apply(ktlintPlugin.pluginId)
    }
}