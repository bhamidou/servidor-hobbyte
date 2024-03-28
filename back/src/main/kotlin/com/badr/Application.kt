package com.badr

import com.badr.plugins.*
import com.badr.utils.Params
import com.badr.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*

fun main() {
    embeddedServer(Netty, port = Params.port, host = Params.ip , module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val tokenManager = TokenManager()

    //Instalo cors porque en el cliente con js da error

    install(CORS) {
        anyHost()
        allowCredentials = true
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod("GET"))
        allowMethod(HttpMethod("PUT"))
        allowMethod(HttpMethod("POST"))
        allowMethod(HttpMethod("DELETE"))
        allowMethod(HttpMethod("OPTIONS"))

    }


    install(Authentication) {
        jwt("auth-jwt") {
            verifier(tokenManager.verifyJWTToken())
            realm = Params.realm
            validate {
                if (it.payload.getClaim("id").asInt()!!.toString().isNotEmpty()){
                    JWTPrincipal(it.payload)
                }
                else {
                    null
                }
            }

        }

    }

    configureSecurity()
    configureSerialization()
    configureRouting()
}

