package dev.brevitz.figurehunter.home

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

private const val HOME_ROUTE = "/"

fun Route.home() {
    get(HOME_ROUTE) {
        call.respondText("Hello from Kotlin backend", ContentType.Text.Html)
    }
}
