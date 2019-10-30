package dev.brevitz.figurehunter.auth.data

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import dev.brevitz.figurehunter.user.domain.User
import java.util.*

object JwtConfig {
    private val secret = System.getenv("JWT_SECRET")
    private val issuer = System.getenv("JWT_ISSUER")
    private val algorithm = Algorithm.HMAC512(secret)

    private const val HOUR_IN_MILLIS = 36_000_00
    private const val VALIDITY_IN_MILLIS = 24 * HOUR_IN_MILLIS

    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .build()

    fun getToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withExpiresAt(Date(System.currentTimeMillis() + VALIDITY_IN_MILLIS))
        .sign(algorithm)
}
