plugins {
    alias(libs.plugins.buildlogic)
    alias(libs.plugins.maven.publish)
}

version = "0.1.0-SNAPSHOT"

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.kotlinx.coroutines)

    api(project(":shared"))
}

publishing {
    publications {
        create<MavenPublication>("coroutinecraftBukkit") {
            groupId = "${project.group}"
            artifactId = "coroutinecraft-bukkit"
            version = "${project.version}"

            from(components["java"])
        }
    }
}