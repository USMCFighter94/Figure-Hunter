import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("stage") {
    dependsOn("installDist")
}

application {
    mainClassName = "dev.brevitz.figurehunter.ServerKt"
}

dependencies {
    compile(project(Project.Authentication.data))
    compile(project(Project.Authentication.domain))
    compile(project(Project.core))
    compile(project(Project.Figure.data))
    compile(project(Project.Figure.domain))
    compile(project(Project.User.data))
    compile(project(Project.User.domain))
    compile(Deps.exposed)
    compile(Deps.kotlin)
    compile(Deps.Ktor.auth)
    compile(Deps.Ktor.core)
    compile(Deps.Ktor.netty)
    compile(Deps.Ktor.gson)
    compile(Deps.logback)
    compile(Deps.postgresql)

    testCompile(group = "junit", name = "junit", version = "4.12")
}
