package com.badr.controllers

import com.badr.models.Respuesta
import com.badr.models.UserModel.User
import com.badr.models.UserModel.UserLogin
import com.badr.service.ServiceMail
import com.badr.service.ServiceUser
import com.badr.utils.ConexionEstatica
import com.badr.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

import io.ktor.server.request.*
import io.ktor.server.routing.*
import java.util.*

object UserController {

    suspend fun login(call: ApplicationCall, us: UserLogin): Any {
        val tokenManager = TokenManager()
        val usuario = ServiceUser.login(us.email, us.password)
        var token: String? = null

        if (usuario == null) {
            call.response.status(HttpStatusCode.NotFound)
            call.respond(Respuesta("Unauthorized", HttpStatusCode.NotFound.value))
        } else {
            token = tokenManager.generateJWTToken(User(usuario.userId, usuario.email, usuario.nombre, usuario.password))
            call.respond(mutableMapOf("token" to token, "email" to usuario.email, "nombre" to usuario.nombre))
            call.response.status(HttpStatusCode.Accepted)

        }
        return call
    }

    suspend fun signup(call: ApplicationCall, us: UserLogin): Any {
        var numConfirmation = this.generateRandomCode(7)
        val insertUser = ServiceUser.insertarUser(us.nombre, us.email, us.password, numConfirmation)
        if (insertUser == 0) {
            ServiceMail.sendMail(
                us.email,
                "Para continuar, debe de confirmar su cuenta con el siguiente código:<br> <strong> ${numConfirmation} </strong>"
            )
            call.response.status(HttpStatusCode.Created)
            call.respond(Respuesta("Usuario creado", HttpStatusCode.Created.value))
        } else {
            call.response.status(HttpStatusCode.ExpectationFailed)
            call.respond(Respuesta("Error al crear el usuario", HttpStatusCode.ExpectationFailed.value))
        }
        return call
    }

    suspend fun getNewCode(call: ApplicationCall, us: UserLogin) {
        var numConfirmation = this.generateRandomCode(10)

        var user = ServiceUser.getUserByEmail(us.email)

        if(user.confirmation != "1"){

            var insertUser = ServiceUser.updateCodeUser(us.email, numConfirmation)
            if (insertUser == 0) {
                ServiceMail.sendMail(
                    us.email,
                    "Para continuar, debe de confirmar su cuenta con el siguiente código:<br> <strong> ${numConfirmation} </strong>"
                )
                call.response.status(HttpStatusCode.Created)
                call.respond(Respuesta("Usuario creado", HttpStatusCode.Created.value))
            } else {
                call.response.status(HttpStatusCode.ExpectationFailed)
                call.respond(Respuesta("Error al confirmar la cuenta", HttpStatusCode.ExpectationFailed.value))
            }

        }else{
            call.response.status(HttpStatusCode.NotAcceptable)
            call.respond(Respuesta("Esta cuenta ya está confirmada", HttpStatusCode.ExpectationFailed.value))
        }
    }

    fun generateRandomCode(length: Int): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val code = StringBuilder(length)

        for (i in 1..length) {
            val randomIndex = random.nextInt(allowedChars.length)
            code.append(allowedChars[randomIndex])
        }

        return code.toString()
    }

    suspend fun confirmCode(call: ApplicationCall, us: User) {

        val insertUser = ServiceUser.getUserByEmailAndCode(us.email, us.confirmation)

        if (insertUser == 1) {
            ServiceMail.sendMail(
                us.email,"Su cuenta está activada, disfrute de todos los servicios del hobbyte!!!")
            var insertUser = ServiceUser.updateCodeUser(us.email, "1")
            call.response.status(HttpStatusCode.Created)
            call.respond(Respuesta("Cuenta activada correctamente", HttpStatusCode.Created.value))
        } else {
            call.response.status(HttpStatusCode.ExpectationFailed)
            call.respond(Respuesta("Error al confirmar la cuenta", HttpStatusCode.ExpectationFailed.value))
        }
    }
}