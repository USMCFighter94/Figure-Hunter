package backend

import backend.figure.FigureRepository
import backend.figure.figure
import backend.home.home
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOG: Logger = LoggerFactory.getLogger("ktor-server")
const val API_V1 = "/api/v1"

private const val DEFAULT_PORT = 8080

fun main() {
    LOG.debug("Starting server...")
    val port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT
    embeddedServer(Netty, port, module = Application::main).start(true)
}

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
