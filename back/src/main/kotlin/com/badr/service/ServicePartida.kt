package com.badr.service

import com.badr.models.Partida.Conexion.PartidaConexion
import com.badr.models.Partida.Partida

object ServicePartida {
    fun insertPartida(partida:Partida):Int{
        return PartidaConexion.insertPrueba(partida.id_usuario, partida.estado)
    }
}