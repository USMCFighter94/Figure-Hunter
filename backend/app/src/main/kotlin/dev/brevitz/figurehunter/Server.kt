package dev.brevitz.figurehunter

import dev.brevitz.figurehunter.auth.data.JwtConfig
import dev.brevitz.figurehunter.auth.data.UserAuthenticationRepository
import dev.brevitz.figurehunter.auth.data.login.login
import dev.brevitz.figurehunter.auth.data.register.register
import dev.brevitz.figurehunter.core.LOG
import dev.brevitz.figurehunter.figure.data.FigureRepository
import dev.brevitz.figurehunter.figure.data.db.FigureDataSource
import dev.brevitz.figurehunter.figure.data.db.FigureLocalDataSource
import dev.brevitz.figurehunter.figure.data.figure
import dev.brevitz.figurehunter.home.home
import dev.brevitz.figurehunter.user.data.UserRepository
import dev.brevitz.figurehunter.user.data.user
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.Principal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

private const val DEFAULT_PORT = 8080

fun main() {
    LOG.debug("Starting server...")
    val port = System.getenv("PORT")?.toInt() ?: DEFAULT_PORT
    embeddedServer(Netty, port, module = Application::main).start(true)
}

fun Application.main() {
    val authenticationRepository = UserAuthenticationRepository()

    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(DefaultHeaders)
    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "ktor.io"
            validate { call ->
                call.payload.getClaim("id").asInt()?.let {
                    authenticationRepository.find(it)
                }
            }
        }
    }

    val cache = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")

    val figureLocalDataSource = FigureLocalDataSource(cache)

    val db = Database.connect(
        System.getenv("JDBC_DATABASE_URL"),
        driver = "org.postgresql.Driver",
        user = System.getenv("JDBC_DATABASE_USERNAME"),
        password = System.getenv("JDBC_DATABASE_PASSWORD")
    )

    val figureDataSource = FigureDataSource(db)

    routing {
        home()
        figure(FigureRepository(figureLocalDataSource, figureDataSource))
        user(UserRepository)
        login(authenticationRepository)
        register(authenticationRepository)
    }
}
