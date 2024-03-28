package com.badr.models.Prueba

import kotlinx.serialization.Serializable

@Serializable
class Prueba{
    var id: Int = 0
    var id_partida = 0
    var tipo: String = ""
    var capacidad: Int = 0
    var destapada: Int = 0
    var orden: Int = 0

    constructor(){}

    constructor(id: Int, tipo: String, capacidad: Int, destapada: Int, orden:Int) {
        this.id = id
        this.tipo = tipo
        this.capacidad = capacidad
        this.destapada = destapada
        this.orden = orden
    }

    constructor(id: Int, id_partida: Int,tipo: String, capacidad: Int, destapada: Int, orden:Int) {
        this.id = id
        this.id_partida = id_partida
        this.tipo = tipo
        this.capacidad = capacidad
        this.destapada = destapada
        this.orden = orden
    }

    constructor(tipo: String, esfuerzo: Int, id_partida:Int) {
        this.tipo = tipo
        this.capacidad = esfuerzo
        this.id_partida = id_partida
    }

    override fun toString(): String {
        return "Prueba(id=$id, id_partida=$id_partida, tipo='$tipo', capacidad=$capacidad, destapada=$destapada, orden=$orden)"
    }
}