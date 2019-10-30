package dev.brevitz.figurehunter.auth.data.register

import dev.brevitz.figurehunter.auth.data.JwtConfig
import dev.brevitz.figurehunter.auth.domain.AuthenticationRepository
import dev.brevitz.figurehunter.auth.domain.TokenResponse
import dev.brevitz.figurehunter.auth.domain.register.RegistrationCredentials
import dev.brevitz.figurehunter.auth.domain.register.RegistrationResult
import dev.brevitz.figurehunter.core.API_V1
import dev.brevitz.figurehunter.core.ErrorResponse
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

private const val REGISTER_ROUTE = "$API_V1/register"

fun Route.register(repository: AuthenticationRepository) {
    post(REGISTER_ROUTE) {
        val credentials = call.receive(RegistrationCredentials::class)

        when (val result = repository.create(credentials)) {
            is RegistrationResult.Success -> call.respond(TokenResponse(JwtConfig.getToken(result.user)))
            is RegistrationResult.Error -> call.respond(HttpStatusCode.Unauthorized, ErrorResponse(result.error))
        }
    }
}
