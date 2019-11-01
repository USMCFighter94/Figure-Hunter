package dev.brevitz.figurehunter.user.domain

import io.ktor.auth.Principal

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val userName: String
) : Principal

// TODO: Remove Dependency on Ktor's Principal from domain layer
