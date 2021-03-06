import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.app)
    kotlin(Plugins.Kotlin.android)
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

    viewBinding {
        isEnabled = true
    }
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(project(Modules.Account.ui))
    implementation(project(Modules.Authentication.ui))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.Core.domain))
    implementation(project(Modules.Core.ui))
    implementation(project(Modules.Home.ui))
    implementation(project(Modules.strings))

    implementation(Deps.appCompat)
    implementation(Deps.constraintLayout)
    implementation(Deps.coreKtx)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.core)
    implementation(Deps.kotlin)
    implementation(Deps.navigation)
    implementation(Deps.navigationUi)
    implementation(Deps.timber)

    testImplementation(Deps.Test.kotlinTest)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.mockWebServer)

    androidTestImplementation(Deps.Test.androidXJUnit)
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.rules)
    androidTestImplementation(Deps.Test.runner)
}
