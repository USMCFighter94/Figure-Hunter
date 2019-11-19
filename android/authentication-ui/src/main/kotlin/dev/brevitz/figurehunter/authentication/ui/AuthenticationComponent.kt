package dev.brevitz.figurehunter.authentication.ui

import dagger.Component
import dev.brevitz.figurehunter.authentication.data.AuthenticationModule
import dev.brevitz.figurehunter.authentication.ui.login.LoginView
import dev.brevitz.figurehunter.authentication.ui.register.RegisterView
import dev.brevitz.figurehunter.core.data.FeatureScope
import dev.brevitz.figurehunter.core.data.di.CoreComponent
import dev.brevitz.figurehunter.core.data.di.DaggerComponent

@Component(dependencies = [CoreComponent::class], modules = [AuthenticationModule::class])
@FeatureScope
interface AuthenticationComponent : DaggerComponent {
    fun inject(view: LoginView)
    fun inject(view: RegisterView)

    companion object {
        const val KEY = "AuthenticationComponent"
    }
}
