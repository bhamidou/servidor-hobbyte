package com.badr.utils

object Constantes {
    val servidor = "localhost"
    val puerto = 3306

    var bbdd = "hobbyte"
    var usuario = "root"
    var passwd = "1234"

    var TablaUser = "usuario"
    var TablaPrueba = "prueba"
    var tablaTablero = "tablero"
    var TablaPartida = "partida"
    var tiposPruebas = arrayListOf<String>("Magia", "Fuerza", "Habilidad")
    var esfuerzoPruebas = arrayListOf<Int>(5, 10, 15, 20, 25, 30, 35, 40, 45, 50)

    var ficheroBitacora = "bitacora.txt"
}