package com.badr.service

import com.badr.models.Heroes.Conexion.HeroeConexion
import com.badr.models.Heroes.Heroe
import com.badr.utils.Constantes

object ServiceHeroe {

    fun getActivedHeroes():MutableList<Heroe>{
        return HeroeConexion.getActivedHeroes()
    }

    fun getHeroeById(id_heroe:Int):Heroe{
        return HeroeConexion.getHeroById(id_heroe)
    }

    fun insertHeroes(arrHeroe: MutableList<Heroe>):Int{
        return HeroeConexion.insertArrHeroes(arrHeroe)
    }

    fun createArrDefaultHeroes(): Int{
        var arrHeroes = ArrayList<Heroe>()
        if(Constantes.heroesDefault.size == Constantes.tiposPruebas.size){
            var i = 0
            while(Constantes.heroesDefault.size>i){
                arrHeroes.add(Heroe(Constantes.heroesDefault[i], Constantes.tiposPruebas[i], Constantes.activedHeroe))
            i++
            }
        }
        return this.insertHeroes(arrHeroes)
    }

}