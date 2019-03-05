package backend

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val kolinResponse = """{
        "id": 1,
        "name": "Kolin Brevitz"
    }"""

    embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing {
            get("/") {
                call.respondText("Hello from Kotlin backend", ContentType.Text.Html)
            }
            get("kolin") {
                call.respondText(kolinResponse, ContentType.Application.Json)
            }
            get("figure") {
                call.respond(Figure(1, "Finn (Jakku)", 1, "The Black Series - The Force Awakens", 2015))
            }
        }
    }.start(true)
}