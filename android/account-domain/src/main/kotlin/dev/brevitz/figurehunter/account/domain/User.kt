package dev.brevitz.figurehunter.account.domain

data class User(
    val userName: String,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
)
