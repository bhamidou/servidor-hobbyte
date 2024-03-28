package com.badr.models.UserModel

import kotlinx.serialization.Serializable

@Serializable
class User{
    var userId: Int = 0
    var email: String = ""
    var nombre: String = ""
    var password: String = ""
    var confirmation: String = ""

    constructor()

    constructor(userId: Int, email: String, nombre: String){
        this.userId = userId
        this.email = email
        this.nombre = nombre
    }
    constructor(userId: Int, email: String, nombre: String, password: String){
        this.userId = userId
        this.email = email
        this.nombre = nombre
        this.password = password
    }
    constructor(email: String, nombre: String, password: String, confirmation: String){
        this.userId = userId
        this.email = email
        this.nombre = nombre
        this.password = password
        this.confirmation = confirmation
    }

    constructor(email:String, confirmation:String){
        this.email = email
        this.confirmation = confirmation
    }
}