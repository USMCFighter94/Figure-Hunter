package dev.brevitz.figurehunter.authentication.data

import dev.brevitz.figurehunter.authentication.domain.Token

data class TokenResponse(val token: String?) {
    fun toDomain() = Token(token!!)
}
