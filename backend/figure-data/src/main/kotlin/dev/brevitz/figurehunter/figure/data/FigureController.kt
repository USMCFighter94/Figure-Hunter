package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.core.API_V1
import dev.brevitz.figurehunter.core.ErrorResponse
import dev.brevitz.figurehunter.figure.domain.FigureRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

private const val FIGURE_ROUTE = "$API_V1/figure"

fun Route.figure(repository: FigureRepository) {
    get("$FIGURE_ROUTE/all") {
        call.respond(repository.getAll())
    }

    get("$FIGURE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter \"id\" not found")
        val figure = repository.get(id)

        if (figure != null) {
            call.respond(figure)
        } else {
            call.respond(HttpStatusCode.NotFound, ErrorResponse("Item with id: $id not found"))
        }
    }
}
