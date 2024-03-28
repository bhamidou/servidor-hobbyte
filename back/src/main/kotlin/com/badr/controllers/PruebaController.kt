package com.badr.controllers

import com.badr.models.Prueba.Prueba
import com.badr.service.ServicePrueba
import com.badr.utils.Constantes
import io.ktor.server.application.*

object PruebaController {

    fun create(call:ApplicationCall, id_partida:Int): MutableList<Prueba> {

        var sizeTablero:Int = Constantes.sizeTablero
        if(call.parameters["size"] != null){
            sizeTablero = call.parameters["size"]!!.toInt()
        }

        var i = 0
        var arrPruebas = ArrayList<Prueba>()
        while(i<sizeTablero){
            arrPruebas.add(Prueba(Constantes.tiposPruebas.random(), Constantes.esfuerzoPruebas.random(), id_partida))
            i++
        }

        ServicePrueba.createPruebas(arrPruebas)

        var pruebas = ServicePrueba.getPruebasByPartidaID(id_partida)

        return pruebas
    }
}