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

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    compile(project(Project.Authentication.domain))
    compile(project(Project.core))
    compile(project(Project.User.domain))

    compile(Deps.bcrypt)
    compile(Deps.exposed)
    compile(Deps.kotlin)
    compile(Deps.Ktor.auth)
    compile(Deps.Ktor.core)
    compile(Deps.Ktor.netty)
    compile(Deps.Ktor.gson)
    compile(Deps.logback)
    compile(Deps.postgresql)

    testCompile(Deps.Test.kotlinTest)
}
