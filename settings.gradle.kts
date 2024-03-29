pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    this.repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "coroutine-craft"


rootDir.listFiles()?.filter { it.isDirectory  }?.forEach { rootProjectFile ->

    if (File(rootProjectFile, "build.gradle.kts").exists()) include(
        rootProjectFile.name
    )

    rootProjectFile.listFiles()?.filter { it.isDirectory && (File(it, "build.gradle.kts").exists()) }
        ?.forEach { projectFile ->

            "${rootProjectFile.name}-${projectFile.name}".also {
                include(it)
                findProject(":$it")?.apply {
                    name = it
                    projectDir = projectFile
                }
            }
        }
}