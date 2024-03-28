package com.badr.utils

object Constantes {
    val servidor = "localhost"
    val puerto = 3306

    var bbdd = "hobbyte"
    var usuario = "root"
    var passwd = "1234"

    var sizeTablero = 20
    var capacidadHeroe = 50
    var activedHeroe = true

    var statusFinishedPartida = 1
    var statusRenderise = 3





    var TablaUser = "usuario"
    var tablaCasilla = "casilla"
    var TablaPrueba = "pruebas"
    var TablaPartida = "partida"
    var tablaHeroe = "heroes"
    var TablaHeroePartida = "heroe_partida"
    var tiposPruebas = arrayListOf<String>("Magia", "Fuerza", "Habilidad")
    var heroesDefault = arrayListOf<String>("Gandalf", "Thorin", "Bilbo")
    var esfuerzoPruebas = arrayListOf<Int>(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)

    var ficheroBitacora = "bitacora.txt"
}