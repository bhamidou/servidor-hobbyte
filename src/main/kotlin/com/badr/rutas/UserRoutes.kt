package com.badr.rutas

import com.badr.controllers.UserController
import com.badr.models.Respuesta
import com.badr.models.UserModel.UserLogin
import com.badr.models.UserModel.User
import com.badr.utils.ConexionEstatica
import com.badr.utils.TokenManager

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var rolesAdmin = arrayListOf<String>("admin","user")
var rolesUser = arrayListOf<String>("user")


fun Route.userRouting() {
//    val tokenManager = TokenManager(HoconApplicationConfig((ConfigFactory.load())))
    val tokenManager = TokenManager()


    route("/login") {
        post {
            val us = call.receive<UserLogin>()
            UserController.login(call, us)
        }
    }
    route("/registrar") {
        post {
            val us = call.receive<UserLogin>()

        }
    }
}
