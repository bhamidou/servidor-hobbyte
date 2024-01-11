package com.badr.models.Tablero

import kotlinx.serialization.Serializable

@Serializable
data class Tablero(var id_partida: Int, var id_prueba: Int, var estado:Int )