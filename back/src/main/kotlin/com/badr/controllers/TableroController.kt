package com.badr.controllers

import com.badr.models.PruebaModel.Prueba
import com.badr.models.Tablero.Tablero
import com.badr.service.ServicePrueba
import com.badr.service.ServiceTablero
import com.badr.utils.Constantes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

object TableroController {
    suspend fun index(call: ApplicationCall){
        var tableros =  ServiceTablero.getTableros()
        call.respond(tableros)
    }

    suspend fun show(call: ApplicationCall, id:String?){
        var tablero: Tablero? =  ServiceTablero.getTablerById(id)
        call.respond(tablero!!)
    }

    suspend fun create(call:ApplicationCall){

        var sizeTablero:Int = 20
        println(call.parameters["size"])
        if(call.parameters["size"] != null){
            sizeTablero = call.parameters["size"]!!.toInt()
        }

//        var i = 0
//        var arrPruebas = ArrayList<Prueba>()
//        while(i<sizeTablero){
//            arrPruebas.add(Prueba(Constantes.tiposPruebas.random(), Constantes.esfuerzoPruebas.random()))
//            i++
//        }
//
//        ServicePrueba.createPrueba(arrPruebas)

        var pruebas = ServicePrueba.getLastPruebas(sizeTablero.toString())

        call.respond(pruebas)
    }
}