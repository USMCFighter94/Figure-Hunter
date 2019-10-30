package dev.brevitz.figurehunter.auth.domain

import dev.brevitz.figurehunter.auth.domain.login.UserCredentials
import dev.brevitz.figurehunter.auth.domain.register.RegistrationCredentials
import dev.brevitz.figurehunter.auth.domain.register.RegistrationResult
import dev.brevitz.figurehunter.user.domain.User

interface AuthenticationRepository {
    fun find(id: Int): User?
    fun find(user: UserCredentials): User?
    fun create(user: RegistrationCredentials): RegistrationResult
}
