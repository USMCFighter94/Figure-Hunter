package dev.brevitz.figurehunter.core.data.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreComponent : DaggerComponent {
    fun serviceCreator(): ServiceCreator
}
