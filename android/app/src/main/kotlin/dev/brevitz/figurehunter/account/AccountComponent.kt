package dev.brevitz.figurehunter.account

import dagger.Component
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.di.CoreComponent
import dev.brevitz.figurehunter.core.data.di.DaggerComponent

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface AccountComponent : DaggerComponent {
    fun inject(fragment: AccountFragment)

    companion object {
        const val KEY = "AccountComponent"
    }
}
