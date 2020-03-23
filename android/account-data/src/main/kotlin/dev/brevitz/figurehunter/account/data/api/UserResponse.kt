package dev.brevitz.figurehunter.account.data.api

import dev.brevitz.figurehunter.account.domain.User

data class UserResponse(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val userName: String?
) {
    fun toDomain() = User(
        id ?: 0,
        userName!!,
        firstName,
        lastName,
        null
    )
}
