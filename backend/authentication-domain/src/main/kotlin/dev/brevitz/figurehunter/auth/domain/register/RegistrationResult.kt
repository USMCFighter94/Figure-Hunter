package dev.brevitz.figurehunter.auth.domain.register

import dev.brevitz.figurehunter.user.domain.User

sealed class RegistrationResult {
    data class Success(val user: User) : RegistrationResult()
    data class Error(val error: String) : RegistrationResult()
}
