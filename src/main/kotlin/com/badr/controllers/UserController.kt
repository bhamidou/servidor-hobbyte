package com.badr.controllers

import com.badr.models.Respuesta
import com.badr.models.UserModel.User
import com.badr.models.UserModel.UserLogin
import com.badr.utils.ConexionEstatica
import com.badr.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

import io.ktor.server.request.*
import io.ktor.server.routing.*

object UserController {

    suspend fun login(call:ApplicationCall, us: UserLogin): Any {

        val tokenManager = TokenManager()
        val usuario = ConexionEstatica.login(us.username, us.password)
        var token:String? = null
        var returnCall:Any? = null

        if (usuario == null) {
            call.response.status(HttpStatusCode.NotFound)
            call.respond(Respuesta("Unauthorized", HttpStatusCode.NotFound.value))
            returnCall = call
        }else{
            token = tokenManager.generateJWTToken(User(usuario.userId, usuario.username,  usuario.password))
            call.respond(mutableMapOf("token" to token, "username" to usuario.username))
            call.response.status(HttpStatusCode.Accepted)
            returnCall = call
        }
        return call
    }
}