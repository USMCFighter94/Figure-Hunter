package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.core.API_V1
import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.figure.domain.Figure
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

private const val FIGURE_ROUTE = "$API_V1/figure"

fun Route.figure(repository: Repository<Figure>) {
    get("$FIGURE_ROUTE/all") {
        call.respond(repository.getAll())
    }

    get("$FIGURE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter \"id\" not found")
        call.respond(repository.get(id))
    }

    post(FIGURE_ROUTE) {
        val request = call.receive(Figure::class)
        call.respond(repository.add(request))
    }
}
