package dev.brevitz.figurehunter.auth.data.login

import dev.brevitz.figurehunter.auth.domain.login.UserCredentials
import io.ktor.auth.UserPasswordCredential

fun UserPasswordCredential.toDomain() = UserCredentials(name, password)
