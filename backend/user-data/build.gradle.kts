import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile(project(Project.core))
    compile(project(Project.Figure.domain))
    compile(project(Project.User.domain))

    compile(Deps.exposed)
    compile(Deps.kotlin)
    compile(Deps.Ktor.auth)
    compile(Deps.Ktor.core)
    compile(Deps.logback)
    compile(Deps.postgresql)

    testCompile(group = "junit", name = "junit", version = "4.12")
}
