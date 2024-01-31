plugins {
    kotlin("jvm") version "1.9.+"
}

group = "dev.peopo"
version = "1.0-SNAPSHOT"
description = "A lightweight coroutine library for folia."

val targetVersion = JavaVersion.VERSION_21
val kotlinVersion = "1.9.+"
val coroutinesVersion = "1.7.+"


allprojects {

    apply(plugin = "java-library")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        // Paper
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        // Folia
        compileOnly("dev.folia:folia-api:1.20.1-R0.1-SNAPSHOT")

        // Kotlin
        compileOnly("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

        testImplementation("org.jetbrains.kotlin:kotlin-test")
    }


    tasks {
        test {
            useJUnitPlatform()

            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        compileKotlin {
            kotlinOptions {
                jvmTarget = targetVersion.ordinal.toString()
            }
        }

        kotlin {
            jvmToolchain(targetVersion.ordinal)
        }

        java {
            sourceCompatibility = targetVersion
            targetCompatibility = targetVersion
            toolchain.languageVersion = JavaLanguageVersion.of(targetVersion.ordinal)
            withSourcesJar()
        }

        javadoc {
            options.encoding = Charsets.UTF_8.name()
        }
        processResources {
            filteringCharset = Charsets.UTF_8.name()
        }

        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release = targetVersion.ordinal
        }
    }
}