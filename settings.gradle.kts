plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "coroutine-craft"

rootDir.listFiles().filter { it.isDirectory && (File(it, "build.gradle.kts").exists()) }.forEach {
    include(it.name)
}


