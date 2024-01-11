package com.badr.service

import com.badr.models.Tablero.Conexion.TableroConexion
import com.badr.models.Tablero.Tablero

object ServiceTablero {

    fun getTableros():ArrayList<Tablero>{
        return TableroConexion.getTableros()
    }

    fun getTablerById(id:String?): Tablero? {
        return TableroConexion.getTableroById(id)
    }
//    fun createTablero(arrPruebas: ArrayList<Tablero>): Int {
//        return TableroConexion.insertPruebas(arrPruebas)
//    }
}