package backend.figure

import backend.API_V1
import backend.Repository
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

private const val FIGURE = "${API_V1}/figure"

fun Route.figure(repository: Repository<Figure>) {
    get("$FIGURE/all") {
        call.respond(repository.getAll())
    }

    get("$FIGURE/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter \"id\" not found")
        call.respond(repository.get(id))
    }

    post(FIGURE) {
        val request = call.receive(Figure::class)
        call.respond(repository.add(request))
    }
}