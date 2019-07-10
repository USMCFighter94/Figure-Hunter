package backend

import backend.repository.FigureRepository
import backend.repository.KolinRepository
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun Application.main() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        get(Endpoints.HOME) {
            call.respondText("Hello from Kotlin backend", ContentType.Text.Html)
        }
        get(Endpoints.KOLIN) {
            call.respondText(KolinRepository.getAll().first(), ContentType.Application.Json)
        }
        get(Endpoints.FIGURE) {
            call.respond(FigureRepository.getAll())
        }
        get(Endpoints.FIGURE + "/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter id not found")
            call.respond(FigureRepository.get(id))
        }
    }
}