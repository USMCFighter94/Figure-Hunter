package dev.brevitz.figurehunter

import android.app.Application
import dev.brevitz.figurehunter.core.data.di.CoreComponent
import dev.brevitz.figurehunter.core.data.di.CoreComponentProvider
import dev.brevitz.figurehunter.core.data.di.DaggerCoreComponent
import timber.log.Timber

class App : Application(), CoreComponentProvider {
    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.factory().create(this)
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())
    }

    override fun coreComponent(): CoreComponent = coreComponent
}
