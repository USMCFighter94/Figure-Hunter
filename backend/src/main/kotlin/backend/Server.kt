package backend

import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOG: Logger = LoggerFactory.getLogger("ktor-server")

fun main() {
    LOG.debug("Starting server...")
    embeddedServer(Netty, 8080, module = Application::main).start(true)
}