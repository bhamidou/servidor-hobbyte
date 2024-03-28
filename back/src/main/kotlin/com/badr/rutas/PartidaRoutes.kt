package com.badr.rutas

import com.badr.controllers.PartidaController
import com.badr.models.Respuesta
import com.badr.service.ServicePartida
import com.badr.service.ServicePrueba
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.partidaRoutes(){


    authenticate("auth-jwt") {

        route("/partida") {
            get {
                PartidaController.showAll(call)
            }

            post {
                PartidaController.newPartida(call)
            }
            route("/{size}"){
                post {
                    PartidaController.newPartida(call)
                }
            }


            route("/{id}") {
                get {
                    var id_prueba = call.parameters["id"]
                    if(id_prueba == null) {
                        call.respond(Respuesta("id vacío en la url", 400))
                    }else{
                        PartidaController.show(call)
                    }
                }

                route("/destapar") {
                    get {
                        // A modo de validator, comprobamos que el paramtro id no sea null
                        var id_prueba = call.parameters["id"]
                        if(id_prueba == null){
                            call.respond(Respuesta("id vacío en la url", 400))
                        }else{
                            /*
                            * comprobamos que la prueba exista, y si es así si le pertenece al usuario
                            * */

                            val principalCall = call.principal<JWTPrincipal>()
                            var id_user = principalCall!!.payload.getClaim("id").toString()
                            var prueba = ServicePrueba.getPruebaByID(id_prueba.toInt())
                            var partida = ServicePartida.getPartidaById(prueba.id_partida)
                            if(partida.id_usuario == id_user.toInt() && partida.id != 0){
                                PartidaController.destapar(call)
                            }else{
                                call.respond(Respuesta("Esta prueba no existe o no le pertence", 400))
                            }
                        }

                    }
                }
            }
        }

        route("/mis-partidas") {
            get {
                PartidaController.showMyPartidas(call)
            }
        }

        route("rendirse/{id}"){
            get{
                var id_prueba = call.parameters["id"]

                if(id_prueba == null){
                    call.respond(Respuesta("id vacío en la url", 400))
                }else{
                    if(ServicePartida.getPartidaById(id_prueba.toInt()).estado == 0){
                        PartidaController.rendirse(call)
                    }else{
                        call.respond(Respuesta("Esta partida ya ha finalziado, no puede rendirse", HttpStatusCode.Unauthorized.value))
                    }
                }
            }
        }
    }

}