package com.badr.service

import com.badr.models.HeroePartida.Conexion.HeroePartidaConexion
import com.badr.models.HeroePartida.HeroePartida

object ServiceHeroePartida {

    fun createHeroePartida(arrHeroePartida:MutableList<HeroePartida>):Int{
        return HeroePartidaConexion.insertArrHeroeUser(arrHeroePartida)
    }

    fun getHeroesOfPartida(id_partida:Int):MutableList<HeroePartida>{
        return HeroePartidaConexion.getArrHeroeUserByPartida(id_partida)
    }

    fun getHeroeOfPartida(id_partida:Int, id_heroe:Int):HeroePartida{
        return HeroePartidaConexion.getHeroeUserByPartida(id_partida, id_heroe)
    }

    fun updateHeroe(heroePartida:HeroePartida):Int{
        return HeroePartidaConexion.updateHeroe(heroePartida)
    }
}