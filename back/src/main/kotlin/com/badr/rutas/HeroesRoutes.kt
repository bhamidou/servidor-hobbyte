package com.badr.rutas

import com.badr.controllers.HeroeController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*


fun Route.heroesRoutes(){


    authenticate("auth-jwt") {
        route ("mis-heroes/{id}"){
            get{
                HeroeController.getHeroes(call)
            }
        }
    }

}