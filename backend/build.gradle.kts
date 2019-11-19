plugins {
    base
    kotlin("jvm") version Versions.kotlin
    id(Deps.dependencyUpdate) version Versions.dependencyUpdate
}

allprojects {
    group = "dev.brevitz"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven { setUrl("http://dl.bintray.com/kotlin/ktor") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
        maven { setUrl("https://dl.bintray.com/kotlin/exposed") }
    }
}

tasks.register("stage") {
    dependsOn("build", "clean")
    tasks.findByName("build")?.mustRunAfter(tasks.findByName("clean"))
}
