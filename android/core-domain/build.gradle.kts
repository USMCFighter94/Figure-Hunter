import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(Plugins.Kotlin.core)
    kotlin(Plugins.Kotlin.extensions)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.Rx.java)
    implementation(Deps.Rx.kotlin)

    testImplementation(Deps.Test.kotlinTest)
}
