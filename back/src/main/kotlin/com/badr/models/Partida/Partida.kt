package com.badr.models.Partida

import kotlinx.serialization.Serializable

@Serializable
class Partida{
    var id: Int = 0
    var id_usuario: Int = 0
    var estado: Int? = null

    constructor(){}
    constructor(id: Int, id_usuario: Int, estado: Int) {
        this.id = id
        this.id_usuario = id_usuario
        this.estado = estado
    }

    constructor(id_usuario: Int, estado: Int) {
        this.id_usuario = id_usuario
        this.estado = estado
    }

    override fun toString(): String {
        return "Partida(id=$id, id_usuario=$id_usuario, estado=$estado)"
    }

}