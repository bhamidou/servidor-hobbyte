package com.badr.utils

import com.badr.models.UserModel.User
import java.sql.*


object ConexionEstatica {
    // ********************* Atributos *************************
    var conexion: Connection? = null

    // Atributo a través del cual hacemos la conexión física.
    var sentenciaSQL: Statement? = null

    // Atributo que nos permite ejecutar una sentencia SQL
    var registros: ResultSet? = null

    fun abrirConexion(): Int {
        var cod = 0
        try {


            val controlador = "com.mysql.cj.jdbc.Driver"
            val URL_BD = "jdbc:mysql://" + Constantes.servidor+":"+Constantes.puerto+"/" + Constantes.bbdd

            Class.forName(controlador)

            // Realizamos la conexión a una BD con un usuario y una clave.
            conexion = DriverManager.getConnection(URL_BD, Constantes.usuario, Constantes.passwd)
            sentenciaSQL = ConexionEstatica.conexion!!.createStatement()
            println("Conexion realizada con éxito")
        } catch (e: Exception) {
            System.err.println("Exception: " + e.message)
            cod = -1
        }
        return cod
    }

    fun cerrarConexion(): Int {
        var cod = 0
        try {
            conexion!!.close()
            println("Desconectado de la Base de Datos")
        } catch (ex: SQLException) {
            cod = -1
        }
        return cod
    }


}