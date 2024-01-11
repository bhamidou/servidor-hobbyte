package com.badr.service

import com.badr.models.PruebaModel.Conexion.PruebaConexion
import com.badr.models.PruebaModel.Prueba
import com.badr.models.PruebaModel.PruebaModel
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes

object ServicePrueba {

    fun getPruebas(): MutableList<Prueba> {
        return PruebaConexion.getPruebas()
    }
    fun createPrueba(arrPruebas:ArrayList<Prueba>){
        PruebaConexion.insertPruebas(arrPruebas)
    }

    fun getLastPruebas(limite:String): MutableList<Prueba>{
        return PruebaConexion.getLastPruebas(limite)
    }
}