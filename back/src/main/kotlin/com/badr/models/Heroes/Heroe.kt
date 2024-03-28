package com.badr.models.Heroes

import kotlinx.serialization.Serializable

@Serializable
class Heroe{
    var id:Int = 0
    var nombre:String = ""
    var tipo_prueba:String = ""
    var activo: Boolean = false
    var capacidad: Int = 0

    constructor()
    constructor(id:Int, nombre: String, tipo_prueba:String){
        this.id = id
        this.nombre = nombre
        this.tipo_prueba = tipo_prueba
    }

    constructor(nombre: String, tipo_prueba:String, activo:Boolean){
        this.nombre = nombre
        this.tipo_prueba = tipo_prueba
        this.activo = activo
    }



}