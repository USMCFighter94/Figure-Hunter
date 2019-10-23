object Plugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val app = "com.android.application"
    const val library = "com.android.library"

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val android = "android"
        const val extensions = "android.extensions"
        const val kapt = "kapt"
    }

    object Apollo {
        const val gradle = "com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo}"
        const val core = "com.apollographql.android"
    }

    object License {
        const val gradle = "com.cookpad.android.licensetools:license-tools-plugin:${Versions.license}"
        const val core = "com.cookpad.android.licensetools"
    }

    const val gradleVersion = "com.github.ben-manes.versions"
}
