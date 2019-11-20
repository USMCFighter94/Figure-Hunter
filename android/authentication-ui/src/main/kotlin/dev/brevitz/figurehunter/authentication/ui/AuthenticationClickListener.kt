package dev.brevitz.figurehunter.authentication.ui

interface AuthenticationClickListener {
    fun goToLogin()
    fun goToRegister()
    fun finished()
}
