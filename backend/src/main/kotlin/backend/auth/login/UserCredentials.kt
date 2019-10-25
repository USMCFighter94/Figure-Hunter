package backend.auth.login

import io.ktor.auth.UserPasswordCredential

data class UserCredentials(val email: String, val password: String)

fun UserPasswordCredential.toDomain() = UserCredentials(name, password)