package backend.auth.register

import backend.user.User

sealed class RegistrationResult {
    data class Success(val user: User) : RegistrationResult()
    data class Error(val error: String) : RegistrationResult()
}