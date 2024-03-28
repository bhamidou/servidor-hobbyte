package com.badr.models.Prueba.Conexion

import com.badr.models.Prueba.Prueba
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object PruebaConexion {
    fun getPruebasById(id_partida: Int): ArrayList<Prueba> {
        var pruebas = ArrayList<Prueba>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba + " where id_partida = " + id_partida
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)

            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                var prueba = Prueba(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getString("tipo_prueba"),
                    ConexionEstatica.registros!!.getInt("capacidad"),
                    ConexionEstatica.registros!!.getInt("destapada"),
                    ConexionEstatica.registros!!.getInt("orden")
                )
                pruebas!!.add(prueba)
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return pruebas
    }

    fun getPruebasOrdenById(id_partida: Int): ArrayList<Prueba> {
        var pruebas = ArrayList<Prueba>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba + " where id_partida = " + id_partida + " and destapada >= 1 order by orden "
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)

            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                var prueba = Prueba(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getString("tipo_prueba"),
                    ConexionEstatica.registros!!.getInt("capacidad"),
                    ConexionEstatica.registros!!.getInt("destapada"),
                    ConexionEstatica.registros!!.getInt("orden")
                )
                pruebas!!.add(prueba)
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return pruebas
    }

    fun getPruebaById(id_prueba: Int): Prueba {
        var prueba = Prueba()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba + " where id = " + id_prueba
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)

            ConexionEstatica.registros = pstmt.executeQuery()
            if (ConexionEstatica.registros!!.next()) {
                prueba = Prueba(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getInt("id_partida"),
                    ConexionEstatica.registros!!.getString("tipo_prueba"),
                    ConexionEstatica.registros!!.getInt("capacidad"),
                    ConexionEstatica.registros!!.getInt("destapada"),
                    ConexionEstatica.registros!!.getInt("orden")
                )
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return prueba
    }

    fun getLastPruebas(limite: String): MutableList<Prueba> {
        var pruebas = ArrayList<Prueba>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba + " ORDER BY id DESC LIMIT " + limite
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
                        ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                var prueba = Prueba(
                    ConexionEstatica.registros!!.getInt("id"),
                    ConexionEstatica.registros!!.getString("tipo_prueba"),
                    ConexionEstatica.registros!!.getInt("capacidad"),
                    ConexionEstatica.registros!!.getInt("destapada"),
                    ConexionEstatica.registros!!.getInt("order")
                )
                pruebas!!.add(prueba)
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return pruebas
    }

    fun getOrderByPartidaId(id_prueba:Int): Int {
        var cod = 0
        try {
            ConexionEstatica.abrirConexion()

            val sentencia = "SELECT orden FROM " + Constantes.TablaPrueba + " where id_partida =" + id_prueba + " order by orden desc limit 1;"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            ConexionEstatica.registros = pstmt.executeQuery()

            if (ConexionEstatica.registros!!.next()) {
                cod = ConexionEstatica.registros!!.getInt("orden")
            }

        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }

    fun insertPrueba(tipo: String, esfuerzo: Int): Int {
        var cod = 0
        val sentencia =
            ("INSERT INTO " + Constantes.TablaPrueba + " (tipo_prueba, esfuerzo) VALUES ('" + tipo + "'," + "'" + esfuerzo + "')")

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

    fun insertPruebas(arrPruebas: MutableList<Prueba>): Int {
        var cod = 0

        val sentencia =
            ("INSERT INTO " + Constantes.TablaPrueba + " (tipo_prueba, capacidad, id_partida, destapada) VALUES (?,?,?,?)")
        var i = 0

        try {
            ConexionEstatica.abrirConexion()
            while (i < arrPruebas.size) {
                val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
                pstmt.setString(1, arrPruebas[i].tipo)
                pstmt.setInt(2, arrPruebas[i].capacidad)
                pstmt.setInt(3, arrPruebas[i].id_partida)
                pstmt.setInt(4, 0)
                pstmt.executeUpdate()
                i++
            }
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }

    fun updateDestapaPrueba(prueba: Prueba): Int {
        var cod = 0

        val sentencia =
            ("UPDATE " + Constantes.TablaPrueba + " SET destapada = ?, orden = ? where id = ?" )

        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setInt(1, prueba.destapada)
            pstmt.setInt(2, prueba.orden)
            pstmt.setInt(3, prueba.id)
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