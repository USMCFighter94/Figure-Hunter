import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.app)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.extensions)
    kotlin(Plugins.Kotlin.kapt)
}

android {
    compileSdkVersion(Sdk.compile)

    defaultConfig {
        applicationId = "dev.brevitz.figurehunter"
        minSdkVersion(Sdk.min)
        targetSdkVersion(Sdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("debug").java.srcDirs("src/debug/kotlin")
        getByName("release").java.srcDirs("src/release/kotlin")
        getByName("test").apply {
            java.srcDirs("src/test/kotlin")
            resources.srcDirs("src/test/resources")
        }
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(project(Deps.Project.Authentication.ui))
    implementation(project(Deps.Project.Core.data))
    implementation(project(Deps.Project.Core.domain))
    implementation(project(Deps.Project.Core.ui))
    implementation(project(Deps.Project.strings))

    implementation(Deps.appCompat)
    implementation(Deps.cardView)
    implementation(Deps.constraintLayout)
    implementation(Deps.coreKtx)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.core)
    implementation(Deps.epoxy)
    implementation(Deps.Glide.core)
    kapt(Deps.Glide.compiler)
    implementation(Deps.kotlin)
    implementation(Deps.lifecycle)
    implementation(Deps.material)
    implementation(Deps.navigation)
    implementation(Deps.navigationUi)
    implementation(Deps.recyclerView)
    implementation(Deps.Rx.android)
    implementation(Deps.Rx.binding)
    implementation(Deps.Rx.java)
    implementation(Deps.Rx.kotlin)
    implementation(Deps.timber)

    testImplementation(Deps.Test.kotlinTest)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.mockWebServer)

    androidTestImplementation(Deps.Test.androidXJUnit)
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.rules)
    androidTestImplementation(Deps.Test.runner)
}
