package dev.brevitz.figurehunter.core.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.brevitz.figurehunter.core.data.network.NetworkClientModule
import dev.brevitz.figurehunter.core.data.network.NetworkModule
import dev.brevitz.figurehunter.core.domain.Storage
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, NetworkClientModule::class, NetworkModule::class])
interface CoreComponent : DaggerComponent {
    fun serviceCreator(): ServiceCreator
    fun storage(): Storage
    fun componentManager(): ComponentManager

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
}
