package com.badr.service


import com.badr.models.Partida.Conexion.PartidaConexion
import com.badr.models.Partida.Partida

object ServicePartida {
    fun getPartidas():ArrayList<Partida>{
        return PartidaConexion.getPartidas()
    }

    fun getPartidaByUser(userId:String):MutableList<Partida>{
        return PartidaConexion.getPartidasUser(userId)
    }

    // obtener la última pertida jugada ordenada por estado
    fun getOrderPartida(id: String):Partida{
        return PartidaConexion.getOrderPartidaByUserId(id)
    }
    fun getPartidaById(id: Int):Partida{
        return PartidaConexion.getPartidaById(id)
    }
    fun newPartida(id: String):Int{
        return PartidaConexion.insertPrueba(id)
    }

    fun updatePartida(estado:Int, id_partida:Int):Int{
        return PartidaConexion.updatePartida(estado, id_partida)
    }
}