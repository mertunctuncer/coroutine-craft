plugins {
    `kotlin-dsl`
    alias(libs.plugins.ktlint)
}

group = "dev.peopo.coroutinecraft.buildlogic"
version = "0.1"


dependencies {
    implementation(libs.gradle.kotlin)
}

gradlePlugin {
    plugins {
        create("buildlogic") {
            id = "dev.peopo.buildlogic"
            version = project.version
            implementationClass = "dev.peopo.buildlogic.BuildLogicPlugin"
        }
    }
}