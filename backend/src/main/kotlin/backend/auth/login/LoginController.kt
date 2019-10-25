package backend.auth.login

import backend.API_V1
import backend.ErrorResponse
import backend.auth.AuthenticationRepository
import backend.auth.JwtConfig
import backend.auth.TokenResponse
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

private const val LOGIN_ROUTE = "$API_V1/login"

fun Route.login(repository: AuthenticationRepository) {
    post(LOGIN_ROUTE) {
        val credentials = call.receive(UserPasswordCredential::class).toDomain()

        val token = repository.find(credentials)?.let { JwtConfig.getToken(it) }

        if (!token.isNullOrBlank()) {
            call.respond(TokenResponse(token))
        } else {
            call.respond(HttpStatusCode.Unauthorized, ErrorResponse("Incorrect username or password."))
        }
    }
}