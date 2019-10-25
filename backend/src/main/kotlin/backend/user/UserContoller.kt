package backend.user

import backend.API_V1
import backend.Repository
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

private const val USER_ROUTE = "$API_V1/user"

fun Route.user(repository: Repository<User>) {
    get("$USER_ROUTE/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter \"id\" not found")
        call.respond(repository.get(id))
    }
}