package backend.auth

import backend.auth.login.UserCredentials
import backend.auth.register.RegistrationCredentials
import backend.auth.register.RegistrationResult
import backend.user.User

interface AuthenticationRepository {
    fun find(id: Int): User?
    fun find(user: UserCredentials): User?
    fun create(user: RegistrationCredentials): RegistrationResult
}