package dev.brevitz.figurehunter

import android.app.Application
import dev.brevitz.figurehunter.core.data.di.CoreComponent
import dev.brevitz.figurehunter.core.data.di.CoreComponentProvider
import dev.brevitz.figurehunter.core.data.di.DaggerCoreComponent
import timber.log.Timber

class App : Application(), CoreComponentProvider {
    private val coreComponent = DaggerCoreComponent.create()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(CrashReportingTree())
    }

    override fun coreComponent(): CoreComponent = coreComponent
}
