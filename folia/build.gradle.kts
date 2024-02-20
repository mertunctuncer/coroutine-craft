plugins {
    alias(libs.plugins.buildlogic)

}

dependencies {
    compileOnly(libs.folia.api)
    compileOnly(libs.kotlinx.coroutines)

    api(project(":shared"))
}