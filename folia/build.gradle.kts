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
        create<MavenPublication>("folia") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}