package backend.auth.register

import backend.API_V1
import backend.ErrorResponse
import backend.auth.AuthenticationRepository
import backend.auth.JwtConfig
import backend.auth.TokenResponse
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