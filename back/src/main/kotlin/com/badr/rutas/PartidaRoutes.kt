package com.badr.rutas

import com.badr.controllers.PartidaController
import com.badr.controllers.TableroController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.partidaRoutes(){

    route("/partida") {
        post {
            PartidaController.create(call)
        }
    }
}