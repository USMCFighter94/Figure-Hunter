package backend

import io.ktor.application.call
import io.ktor.http.ContentType
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
        routing {
            get("/") {
                call.respondText("Hello from Kotlin backend", ContentType.Text.Html)
            }
            get("kolin") {
                call.respondText(kolinResponse, ContentType.Application.Json)
            }
        }
    }.start(true)
}