package com.badr.models.UserModel

import kotlinx.serialization.Serializable

@Serializable
class UserLogin{
    var nombre:String = ""
    var email: String = ""
    var password: String = ""
    constructor(nombre:String, email: String, password: String){
        this.nombre = nombre
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String){
        this.email = email
        this.password = password
    }
}