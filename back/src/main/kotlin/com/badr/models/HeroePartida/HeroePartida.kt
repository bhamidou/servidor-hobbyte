package com.badr.models.HeroePartida


class HeroePartida {
    var id_partida: Int = 0
    var id_heroe: Int = 0
    var capacidad: Int = 0

    constructor()
    constructor(id_partida:Int, id_heroe:Int, capacidad: Int){
        this.id_partida = id_partida
        this.id_heroe = id_heroe
        this.capacidad = capacidad
    }

    override fun toString(): String {
        return "HeroePartida(id_partida=$id_partida, id_heroe=$id_heroe, capacidad=$capacidad)"
    }

}