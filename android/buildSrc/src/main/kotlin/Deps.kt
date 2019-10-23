object Deps {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val navigation = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    object Rx {
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
        const val binding = "com.jakewharton.rxbinding2:rxbinding-kotlin:${Versions.rxBinding}"
        const val java = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    }

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Test {
        const val androidXJUnit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val kotlinTest = "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlinTest}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
        const val runner = "androidx.test:runner:${Versions.testRunner}"
        const val rules = "androidx.test:rules:${Versions.testRules}"
    }
}
