package backend

import backend.figure.FigureRepository
import backend.figure.figure
import backend.home.home
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing

const val API_V1 = "/api/v1"

fun Application.main() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        home()
        figure(FigureRepository)
    }
}