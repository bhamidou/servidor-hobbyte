package com.badr.rutas


import com.badr.controllers.TableroController
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.tableroRoutes(){

    route("/tablero") {
        get {
            TableroController.index(call)
        }
    }

    route("/tablero/{id}") {
        get {
            var id = call.parameters["id"]
            TableroController.show(call, id)
        }
    }

    route("/tablero/{id}") {
        get {
            var id = call.parameters["id"]
            TableroController.show(call, id)
        }
    }
}