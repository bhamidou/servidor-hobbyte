package com.badr.controllers

import com.badr.models.Partida.Partida
import com.badr.models.Respuesta
import com.badr.models.HeroePartida.HeroePartida
import com.badr.models.Heroes.Heroe
import com.badr.service.*
import com.badr.utils.Constantes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

object PartidaController {
    suspend fun show(call: ApplicationCall) {
        var partida= ServicePrueba.getPruebasByPartidaID(call.parameters["id"]!!.toInt())
        var estado = this.checkWin(call.parameters["id"]!!.toInt())


        call.respond(partida)
    }

    suspend fun rendirse(call: ApplicationCall) {
        var partida= ServicePartida.updatePartida(Constantes.statusRenderise,call.parameters["id"]!!.toInt())
        if(partida == 0){
            call.respond(Respuesta("Se ha rendido", 200))
        }else{
            call.respond(Respuesta("No ha sido posible rendirse", 400))
        }
    }

    suspend fun newPartida(call: ApplicationCall) {
        val principalCall = call.principal<JWTPrincipal>()
        var id = principalCall!!.payload.getClaim("id").toString()


        var partida: Partida = ServicePartida.getOrderPartida(id)

        /**
         * Si el usuario en el pasado tiene un partida, se comprueba que
         * esté finalizada, y si nunca ha jugado, el result set nos devuelve null
         * */

        if (partida.estado == null || partida.estado!! >= Constantes.statusFinishedPartida) {
            ServicePartida.newPartida(id)

            var myNewPartida = ServicePartida.getPartidaByUser(id)

            PruebaController.create(call, myNewPartida[0].id)

            HeroeController.createHeroePartida(myNewPartida[0].id)

            call.respond(HttpStatusCode.Created, Respuesta("Partida creada correctamente", 201))
        } else {
            call.respond(HttpStatusCode.Unauthorized, Respuesta("Ya tienes una partida en curso", 401))
        }
    }




    /*
    *
    * He intentando por todos los medios simplificar, modularizar y optimizar esta función,
    * pero es que no he sido capaz, es enorme esta función, y no tengo manera de simplificarla más.
    *
    * No me ha gustado nada tener ifs dentro de ifs, se que no es código limpio, pero en este momento
    * no se me ocurre otra manera mejorarlo.
    *
    *
    *
    *
    *
    * Obtenemos por un lado la prueba, que tiene la siguiente forma:
    *
    *       id_prueba:366 	destapada:0 	tipo_prueba:Habilidad 	capacidad:20 	id_partida:41 	orden:0
    *
    * Destapada: 0 = No ha sido destapada.
    * Destapada: 1 = Ha sido destapada y el usuario ha perdido.
    * Destapada: 2 = Ha sido destapada y el usuario ha ganado.
    *
    * El orden es para obtener las perdidas consecutivas, se incremeta siempre +1,
    * pero this.checkRachaPerdidas(id_partida) comprueba si hay una racha de pérdidas.
    *
    * por otro el array de HeroePartida que tiene la siguiente forma:
    *
    *       id_heroe:2 	id_partida:41 	capacidad: 50
    * */
    suspend fun destapar(call: ApplicationCall) {


        var id_prueba = call.parameters["id"]

        var prueba = ServicePrueba.getPruebaByID(id_prueba!!.toInt())
        var partida = ServicePartida.getPartidaById(prueba.id_partida)

        if (partida.estado == 0) {
            if (prueba.destapada == 0) {

                var arrHeroeUser = ServiceHeroePartida.getHeroesOfPartida(prueba.id_partida)
                var arrHeroe = ArrayList<Heroe>()

                /*
                * Guardamos en un array los heroes que tenga el usuario para jugar,
                * porque es posible que un usuario tenga unos heroes activados,
                * y el adminsitrador desactivar uno, pero ese heroe anteriormente
                * se le había entregado al usuario como activo,
                *  por lo que tiene derecho a jugar con él.
                *
                * */
                for (hero in arrHeroeUser) {
                    arrHeroe.add(ServiceHeroe.getHeroeById(hero.id_heroe))
                }

                //Obtenemos la prueba con mayor orden para luego sumarle 1 a la prueba a destapar.
                var lastPruebaById = ServicePrueba.getLastPruebaById(prueba.id_partida)

                var capacidadPrueba = prueba.capacidad
                var capacidadHeroe = 0
                var id_heroe = 0

                /*
                * Tengo por un lado los heroes con los tipos en arrHeroe,
                * por otro lado arrHeroePartida que tiene los heroes con sus capacidades
                * por lo que sacamos las capacidades de la prueba y del heroe la capacidad y el id
                * */

                for (hereo in arrHeroe) {
                    for (heroeUser in arrHeroeUser) {
                        if (hereo.tipo_prueba == prueba.tipo && heroeUser.id_heroe == hereo.id) {

                            id_heroe = heroeUser.id_heroe
                            capacidadHeroe = heroeUser.capacidad
                        }
                    }
                }

                //Se comprueba que el heroe si sigue con capacidad como para enfrentarse a la prueba

                if (this.checkHeroe(prueba.id_partida, id_heroe)) {


                    // El logro es si heroe ha ganado el juego o no

                    var logro = this.game(capacidadHeroe, capacidadPrueba)


                    var pruebaUpdated = prueba

                    pruebaUpdated.orden = lastPruebaById + 1

                    var capacidad = 0

                    //para luego devolver si se ha ganado o perdido
                    var winner = "perdido"

                    if (logro) {
                        capacidad = capacidadHeroe - capacidadPrueba

                        //para no tener capcidad negativa
                        if (capacidad < 0) {
                            capacidad = 0
                        }
                        pruebaUpdated.destapada = 2
                        winner = "ganado"
                    } else {
                        pruebaUpdated.destapada = 1
                    }

                    //Se guarda el nuevo estado del heroe tras pasar las pruebas.
                    var heroePartida = HeroePartida(prueba.id_partida, id_heroe, capacidad)
                    ServiceHeroePartida.updateHeroe(heroePartida)

                    //se actualiza el estado de la partida
                    ServicePrueba.updatePrueba(pruebaUpdated)

                    //Comprobamos tras las actualizaciones si se ha ganado la partida
                    if (this.checkWin(partida.id)) {

                        ServicePartida.updatePartida(2, partida.id)
                        call.respond(Respuesta("Ha ganado la partida, enhorabuena", 200))

                    } else {

                        // Si no ha ganado, le indicamos como ha quedado en su prueba
                        call.respond(Respuesta("Ha ${winner}, su heroe le queda ${capacidad} de capacidad", 200))
                    }

                } else {
                    //Si el heroe está muerto comprobamos la racha de perdidas
                    if (this.checkRachaPerdidas(partida.id)) {
                        ServicePartida.updatePartida(1, partida.id)
                        call.respond(Respuesta("Ha perdido de forma consecutiva 5 pruebas, pierde la partida",200))
                    } else {
                        //Esta vez realizamos el checkeo de todos los heroes no de solo uno, para comprobar que sigan vivos
                        if (this.checkHeroes(partida.id, arrHeroeUser)) {

                            var pruebaUpdated = prueba

                            pruebaUpdated.destapada = 1
                            pruebaUpdated.orden = lastPruebaById + 1
                            ServicePrueba.updatePrueba(pruebaUpdated)
                            call.respond(Respuesta("Su heroe se encuentra sin capacidad, pierde la prueba",200))

                        } else {
                            ServicePartida.updatePartida(1, partida.id)
                            call.respond(Respuesta("Ha perdido la partida, debe de crear una nueva partida",401))
                        }
                    }
                }
            } else {
                call.respond(Respuesta("La prueba ya ha sido destapada, destapa otra prueba", 400))
            }
        } else {
            call.respond(Respuesta("La partida ya ha terminado, debe de crear una nueva partida", 400))

        }

    }

    fun game(capacidadHeroe: Int, capacidadPrueba: Int): Boolean {

        var logro: Boolean
        if (capacidadHeroe > capacidadPrueba) {
            logro = (1..10).random() < 9
        } else if (capacidadHeroe == capacidadPrueba) {
            logro = (1..10).random() < 7
        } else {
            logro = (1..10).random() < 5
        }

        return logro
    }

    fun checkRachaPerdidas(id_partida: Int): Boolean {
        var pruebas = ServicePrueba.getPruebasWithOrden(id_partida)

        var aux = 0
        var i = 0

        while (pruebas.size > i && aux <= 5) {
            if (pruebas[i].destapada == 1) {
                aux++
            } else {
                aux = 0
            }
            i++
        }

        return aux >= 5
    }

    fun checkWin(id_partida: Int): Boolean {
        var pruebas = ServicePrueba.getPruebasByPartidaID(id_partida)

        var aux = 0
        var i = 0

        while (pruebas.size > i) {
            println(pruebas[i])
            if (pruebas[i].destapada == 2) {
                aux++
            }
            i++
        }


        return aux >= (pruebas.size / 2)

    }

    fun checkHeroe(id_partida: Int, id_heroe: Int): Boolean {
        return ServiceHeroePartida.getHeroeOfPartida(id_partida, id_heroe).capacidad > 0
    }

    fun checkHeroes(id_partida: Int, arrHeroes: MutableList<HeroePartida>): Boolean {
        var check = true
        var i = 0
        while (arrHeroes.size > i && check) {
            if (this.checkHeroe(id_partida, arrHeroes[i].id_heroe)) {
                check = false
            }
            i++
        }

        return check
    }


    suspend fun myPartida(call: ApplicationCall) {
        var partida: Partida? = ServicePartida.getPartidaById(call.parameters["id"]!!.toInt())
        call.respond(partida!!)
    }

    suspend fun showAll(call: ApplicationCall) {
        var casilla: ArrayList<Partida> = ServicePartida.getPartidas()
        call.respond(casilla!!)
    }

    suspend fun showMyPartidas(call: ApplicationCall) {
        val principalCall = call.principal<JWTPrincipal>()
        var id = principalCall!!.payload.getClaim("id").toString()
        var casilla: MutableList<Partida> = ServicePartida.getPartidaByUser(id)
        call.respond(casilla!!)
    }


}