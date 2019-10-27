package dev.brevitz.figurehunter.home

import dagger.Component
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.di.CoreComponent
import dev.brevitz.figurehunter.core.data.di.DaggerComponent
import dev.brevitz.figurehunter.core.data.home.HomeModule

@FeatureScope
@Component(dependencies = [CoreComponent::class], modules = [HomeModule::class])
interface HomeComponent : DaggerComponent {
    fun inject(fragment: HomeFragment)
}
