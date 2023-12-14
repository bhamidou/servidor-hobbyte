package com.badr.rutas

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


fun Route.userRouting(){  //Esta ruta se incluirá en el archivo Routing.
//    val tokenManager = TokenManager(HoconApplicationConfig((ConfigFactory.load())))
   val tokenManager = TokenManager()

    route("/login") {
        post{
            val us = call.receive<UserLogin>()
            val usuario = ConexionEstatica.login(us.username, us.password)

            if (usuario == null) {
                call.response.status(HttpStatusCode.NotFound)
                return@post call.respond(Respuesta("Unauthorized", HttpStatusCode.NotFound.value))
            }else{
                val token = tokenManager.generateJWTToken(User(usuario.userId, usuario.username,  usuario.password))
                call.respond(mapOf("token" to token, "username" to usuario.username))
                call.response.status(HttpStatusCode.Accepted)
                return@post call.respond(mapOf("token" to token, "username" to usuario.username))
            }
        }
    }
}
