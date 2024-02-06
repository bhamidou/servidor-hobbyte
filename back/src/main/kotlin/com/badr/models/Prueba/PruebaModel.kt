package com.badr.models.PruebaModel

import kotlinx.serialization.Serializable

@Serializable
class PruebaModel{
    var id: Int = 0
    var tipo: String = ""
    var esfuerzo: Int = 0

    constructor(id: Int, tipo: String, esfuerzo: Int) {
        this.id = id
        this.tipo = tipo
        this.esfuerzo = esfuerzo
    }

    constructor(tipo: String, esfuerzo: Int) {
        this.tipo = tipo
        this.esfuerzo = esfuerzo
    }
}