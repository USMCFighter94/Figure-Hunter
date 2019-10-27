package dev.brevitz.figurehunter.core.data.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CoreModule {
    @Provides
    @Singleton
    fun componentManager() = ComponentManager()
}
