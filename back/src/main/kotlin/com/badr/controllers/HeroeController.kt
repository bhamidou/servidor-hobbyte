package com.badr.controllers

import com.badr.models.HeroePartida.HeroePartida
import com.badr.models.Heroes.Heroe
import com.badr.models.Respuesta
import com.badr.service.ServiceHeroe
import com.badr.service.ServiceHeroePartida
import com.badr.utils.Constantes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

object HeroeController {
    fun createHeroePartida(id_partida: Int){

        var heroes = ServiceHeroe.getActivedHeroes()

        //Primero se comprueba que existan heroes
        if(heroes.size == 0){

            //Si no hay heroes, se insertaran heroes deault
            if(ServiceHeroe.createArrDefaultHeroes() == 1){
                heroes = ServiceHeroe.getActivedHeroes()
            }
        }


        var arrHeroePartida = ArrayList<HeroePartida>()

        for(heroe in heroes ){
            arrHeroePartida.add(HeroePartida(id_partida, heroe.id, Constantes.capacidadHeroe))
        }

        ServiceHeroePartida.createHeroePartida(arrHeroePartida)

    }

    suspend fun getHeroes(call:ApplicationCall){
        var id_partida = call.parameters["id"]
        var heroePartida = ServiceHeroePartida.getHeroesOfPartida(id_partida!!.toInt())
        var arrHeroe = ArrayList<Heroe>()
        for(heroe in heroePartida){
            var hero = ServiceHeroe.getHeroeById(heroe.id_heroe)
            hero.capacidad = heroe.capacidad
            arrHeroe.add(hero)
        }
        call.respond(HttpStatusCode.OK, arrHeroe)
    }
}