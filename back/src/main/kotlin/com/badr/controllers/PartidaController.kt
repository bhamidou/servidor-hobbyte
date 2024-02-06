package com.badr.controllers

import com.badr.models.Partida.Partida
import com.badr.models.Respuesta
import com.badr.models.Casilla.Casilla
import com.badr.service.ServicePartida
import com.badr.service.ServiceTablero
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

object PartidaController {
    suspend fun show(call: ApplicationCall){
        var partida: Partida? =  ServicePartida.getPartidaById(call.parameters["id"].toString())
        call.respond(partida!!)
    }

    suspend fun newPartida(call: ApplicationCall){
        var id = 1
        var partida: MutableList<Partida> =  ServicePartida.getLastPartida(id.toString())
    println(partida)
        if(partida.size==1 && partida[0].estado == 3){
            ServicePartida.newPartida(id.toString())
            call.respond( HttpStatusCode.Created, Respuesta("Partida creada correctamente", 201))
        }else if(partida.size==0){
            ServicePartida.newPartida(id.toString())
            call.respond( HttpStatusCode.Created, Respuesta("Partida creada correctamente", 201))
        }else{
            call.respond( HttpStatusCode.Unauthorized, Respuesta("Ya tienes una partida en curso", 401))
        }
    }

    suspend fun myPartida(call: ApplicationCall){
        var partida: Partida? =  ServicePartida.getPartidaById(call.parameters["id"].toString())
        call.respond(partida!!)
    }

    suspend fun showAll(call: ApplicationCall){
        var casilla: ArrayList<Partida> =  ServicePartida.getPartidas()
        call.respond(casilla!!)
    }

//    suspend fun create(call: ApplicationCall){
//        var partidaCall = call.receive<Partida>()
//        var partida = Partida(partidaCall.id_usuario,partidaCall.estado)
//        var insert = ServicePartida.(partidaCall)
//        if(insert==0){
//            call.response.status(HttpStatusCode.Created)
//            call.respond(Respuesta("Partida creada correctamente", HttpStatusCode.Created.value))
//        }else{
//            call.response.status(HttpStatusCode.NotAcceptable)
//            call.respond(Respuesta("No se ha podido crear la partida", HttpStatusCode.NotAcceptable.value))
//        }
//    }
}