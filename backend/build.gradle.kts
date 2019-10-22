import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group  = "dev.brevitz"
version  = "1.0.0"

plugins {
    application
    kotlin("jvm") version Versions.kotlin
}

repositories {
    mavenCentral()
    maven { setUrl("http://dl.bintray.com/kotlin/ktor") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "backend.ServerKt"
}

dependencies {
    compile(Deps.kotlin)
    compile(Deps.Ktor.core)
    compile(Deps.Ktor.netty)
    compile(Deps.Ktor.gson)
    compile(Deps.logback)

    testCompile(group = "junit", name = "junit", version = "4.12")
}