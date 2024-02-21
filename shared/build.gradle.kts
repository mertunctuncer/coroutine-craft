plugins {
    alias(libs.plugins.buildlogic)
}

version = "0.1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.kotlinx.coroutines)
}