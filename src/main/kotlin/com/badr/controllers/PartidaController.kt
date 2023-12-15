package com.badr.controllers

import com.badr.models.Partida.Partida
import com.badr.models.Respuesta
import com.badr.models.Tablero.Tablero
import com.badr.service.ServicePartida
import com.badr.service.ServiceTablero
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

object PartidaController {
    suspend fun show(call: ApplicationCall, id:String?){
        var tablero: Tablero? =  ServiceTablero.getTablerById(id)
        call.respond(tablero!!)
    }

    suspend fun create(call: ApplicationCall){
        var partidaCall = call.receive<Partida>()
        var partida = Partida(partidaCall.id_usuario,partidaCall.estado)
        var insert = ServicePartida.insertPartida(partidaCall)
        if(insert==0){



            call.response.status(HttpStatusCode.Created)
            call.respond(Respuesta("Partida creada correctamente", HttpStatusCode.Created.value))
        }else{
            call.response.status(HttpStatusCode.NotAcceptable)
            call.respond(Respuesta("No se ha podido crear la partida", HttpStatusCode.NotAcceptable.value))
        }
    }
}