import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.library)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.extensions)
    kotlin(Plugins.Kotlin.kapt)
}

android {
    compileSdkVersion(Sdk.compile)

    defaultConfig {
        minSdkVersion(Sdk.min)
        targetSdkVersion(Sdk.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName("release") {
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
    implementation(project(Modules.Home.data))
    implementation(project(Modules.Home.domain))
    implementation(project(Modules.Core.data))
    implementation(project(Modules.Core.domain))
    implementation(project(Modules.Core.ui))

    implementation(Deps.appCompat)
    implementation(Deps.constraintLayout)
    implementation(Deps.coreKtx)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.core)
    implementation(Deps.epoxy)
    implementation(Deps.kotlin)
    implementation(Deps.material)
    implementation(Deps.recyclerView)
    implementation(Deps.Rx.android)
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
