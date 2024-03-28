package com.badr.models.Partida.Conexion

import com.badr.models.Partida.Partida
import com.badr.models.Prueba.Prueba
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object PartidaConexion {
    fun getPartidas(): ArrayList<Partida> {
        var partidas = ArrayList<Partida>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPartida
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            ConexionEstatica.registros = pstmt.executeQuery()
            println(ConexionEstatica)
            while (ConexionEstatica.registros!!.next()) {
                var partida = Partida(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getInt("id_usuario"),
                    ConexionEstatica.registros!!.getInt("estado")
                )
                partidas!!.add(partida)
            }

        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return partidas
    }

    fun getPartidasUser(userId: String): MutableList<Partida> {
        var partidas = ArrayList<Partida>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia =
                "SELECT * FROM " + Constantes.TablaPartida + " where id_usuario = " + userId + " order by estado"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            ConexionEstatica.registros = pstmt.executeQuery()
            println(ConexionEstatica)
            while (ConexionEstatica.registros!!.next()) {
                var partida = Partida(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getInt("id_usuario"),
                    ConexionEstatica.registros!!.getInt("estado")
                )
                partidas!!.add(partida)
            }

        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return partidas
    }

    fun getOrderPartidaByUserId(userId: String): Partida {
        var partida = Partida()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia =
                "SELECT * FROM " + Constantes.TablaPartida + " where id_usuario = " + userId + " order by estado limit 1"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            ConexionEstatica.registros = pstmt.executeQuery()
            println(ConexionEstatica)
            while (ConexionEstatica.registros!!.next()) {
                partida = Partida(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getInt("id_usuario"),
                    ConexionEstatica.registros!!.getInt("estado")
                )
            }

        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return partida
    }

    fun getPartidaById(id: Int): Partida {
        var partida = Partida()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPartida + " WHERE id = " + id
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            ConexionEstatica.registros = pstmt.executeQuery()
            println(ConexionEstatica)
            while (ConexionEstatica.registros!!.next()) {
                partida = Partida(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getInt("id_usuario"),
                    ConexionEstatica.registros!!.getInt("estado")
                )

            }

        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return partida
    }

    fun insertPrueba(id_usuario: String): Int {
        var cod = 0
        val sentencia = ("INSERT INTO " + Constantes.TablaPartida + " (id_usuario) VALUES ('" + id_usuario + "')")

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

    fun updatePartida(estado: Int, id_partida: Int): Int {
        var cod = 0
        val sentencia =
            ("UPDATE " + Constantes.TablaPartida + " SET estado = ? where id = ?")

        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setInt(1, estado)
            pstmt.setInt(2, id_partida)


            pstmt.executeUpdate()
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }
}