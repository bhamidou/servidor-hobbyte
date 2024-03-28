package com.badr.rutas

import com.badr.controllers.UserController
import com.badr.models.Respuesta
import com.badr.models.UserModel.UserLogin
import com.badr.models.UserModel.User
import com.badr.service.ServicePrueba
import com.badr.service.ServiceUser
import com.badr.utils.ConexionEstatica
import com.badr.utils.TokenManager

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var rolesAdmin = arrayListOf<String>("admin","user")
var rolesUser = arrayListOf<String>("user")


fun Route.userRouting() {
    val tokenManager = TokenManager()


    route("/login") {
        post {
            val us = call.receive<UserLogin>()
            UserController.login(call, us)
        }
    }

    route("/new-code") {
        post {
            val us = call.receive<UserLogin>()
            UserController.getNewCode(call, us)
        }
    }

    route("/check-code") {
        post {
            val us = call.receive<User>()
            UserController.confirmCode(call, us)
        }
    }

    route("/signup") {
        post {
            val us = call.receive<UserLogin>()
            UserController.signup(call, us)
        }
    }

    authenticate("auth-jwt"){
        route("users"){
            get{
                call.respond(HttpStatusCode.OK, ServiceUser.getAll())
            }
        }

        route("user/{id}"){
            delete{
                var id = call.parameters["id"]
                if(id != null){
                    if(ServiceUser.deleteUserById(id) == 0){
                        call.respond(Respuesta("Usuario eliminado correctamente", 200))
                    }else{
                        call.respond(Respuesta("El usuario ni se ha podido eliminar", 400))

                    }
                }else{
                    call.respond(Respuesta("ID del usuario es null", 400))

                }
            }
        }
    }
}
