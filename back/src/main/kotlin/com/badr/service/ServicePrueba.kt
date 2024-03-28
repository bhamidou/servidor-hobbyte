package com.badr.service

import com.badr.models.Prueba.Conexion.PruebaConexion
import com.badr.models.Prueba.Prueba

object ServicePrueba {

    fun getPruebasByPartidaID(id_partida:Int): MutableList<Prueba> {
        return PruebaConexion.getPruebasById(id_partida)
    }

    fun getPruebasWithOrden(id_partida:Int): MutableList<Prueba> {
        return PruebaConexion.getPruebasOrdenById(id_partida)
    }

    fun getPruebaByID(id_prueba:Int): Prueba {
        return PruebaConexion.getPruebaById(id_prueba)
    }

    fun getLastPruebaById(id_partida:Int): Int {
        return PruebaConexion.getOrderByPartidaId(id_partida)
    }


    fun createPruebas(arrPruebas:MutableList<Prueba>):Int{
        return PruebaConexion.insertPruebas(arrPruebas)
    }

    fun updatePrueba(prueba: Prueba):Int{
        return PruebaConexion.updateDestapaPrueba(prueba)
    }

    fun getLastPruebas(limite:String): MutableList<Prueba>{
        return PruebaConexion.getLastPruebas(limite)
    }
}