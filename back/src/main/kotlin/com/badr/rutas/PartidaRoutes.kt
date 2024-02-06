package com.badr.rutas

import com.badr.controllers.PartidaController
import com.badr.controllers.TableroController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.partidaRoutes(){

    route("/partida") {
        get {
            PartidaController.showAll(call)
        }
        post {
            PartidaController.newPartida(call)
        }
        route("/{id}") {
            get{
                PartidaController.show(call)
            }
        }
    }

    route("/mis-partida") {
        get {
            PartidaController.show(call)
        }
    }

}