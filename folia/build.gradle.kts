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

    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/mertunctuncer/coroutine-craft")
            credentials {
                username = property("mavenUser").toString()
                password = property("mavenKey").toString()
            }
        }
    }

    publications {
        create<MavenPublication>("folia") {
            groupId = rootProject.group.toString()
            artifactId = "coroutinecraft-folia"
            version = project.version.toString()

            from(components["java"])
        }
    }
}