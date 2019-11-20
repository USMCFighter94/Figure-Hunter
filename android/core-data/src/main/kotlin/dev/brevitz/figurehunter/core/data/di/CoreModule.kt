package dev.brevitz.figurehunter.core.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.brevitz.figurehunter.core.data.SharedPreferences
import dev.brevitz.figurehunter.core.domain.Storage
import javax.inject.Singleton

@Module
object CoreModule {
    @Provides
    @Singleton
    fun componentManager() = ComponentManager()

    @Provides
    @Singleton
    fun storage(context: Context): Storage = SharedPreferences(context)
}
