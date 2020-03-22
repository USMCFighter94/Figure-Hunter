package dev.brevitz.figurehunter.core.data.di

import dagger.Binds
import dagger.Module
import dev.brevitz.figurehunter.core.data.SharedPreferences
import dev.brevitz.figurehunter.core.domain.Storage
import javax.inject.Singleton

@Module
interface CoreModule {
    @Binds
    @Singleton
    fun storage(sharedPreferences: SharedPreferences): Storage
}
