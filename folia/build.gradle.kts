version = "0.1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.folia.api)
    compileOnly(libs.kotlinx.coroutines)
    api(project(":shared"))
}

kotlin {
    compilerOptions {
        jvmToolchain(21)
        explicitApi()
    }
}