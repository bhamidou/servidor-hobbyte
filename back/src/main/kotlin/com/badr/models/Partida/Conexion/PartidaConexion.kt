package com.badr.models.Partida.Conexion

import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object PartidaConexion {
    fun insertPrueba(id_usuario: Int, estado: Int): Int{
        var cod = 0
        val sentencia = ("INSERT INTO " + Constantes.TablaPartida + " (id_usuario, estado) VALUES ('" + id_usuario + "'," + "'" + estado + "')")

        try {
            ConexionEstatica.abrirConexion()
            ConexionEstatica.sentenciaSQL!!.executeUpdate(sentencia)
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }
}