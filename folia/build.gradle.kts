plugins {
    alias(libs.plugins.buildlogic)
    alias(libs.plugins.maven.publish)
}

version = "0.1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.folia.api)
    compileOnly(libs.kotlinx.coroutines)

    api(project(":shared"))
}

publishing {
    publications {
        create<MavenPublication>("coroutinecraftFolia") {
            groupId = "${project.group}"
            artifactId = "coroutinecraft-folia"
            version = "${project.version}"

            from(components["java"])
        }
    }
}