package dev.brevitz.figurehunter.authentication.ui

import com.airbnb.epoxy.EpoxyController

class AuthenticationController(private val clickListener: AuthenticationClickListener) : EpoxyController() {
    override fun buildModels() {
        LoginView { clickListener.goToRegister() }
            .id(LoginView.ID)
            .addTo(this)

        RegisterView { clickListener.goToLogin() }
            .id(RegisterView.ID)
            .addTo(this)
    }
}
