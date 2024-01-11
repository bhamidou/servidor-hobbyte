package com.badr.models.PruebaModel.Conexion

import com.badr.models.PruebaModel.Prueba
import com.badr.models.PruebaModel.PruebaModel
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object PruebaConexion {
    fun getPruebas(): ArrayList<Prueba> {
        var pruebas = ArrayList<Prueba>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            // pstmt.setInt(1, 30000)
            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                var prueba = Prueba(ConexionEstatica.registros!!.getInt("id"), ConexionEstatica.registros!!.getString("tipo"), ConexionEstatica.registros!!.getInt("esfuerzo"))
                pruebas!!.add(prueba)
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return pruebas
    }

    fun getLastPruebas(limite:String): MutableList<Prueba> {
        var pruebas = ArrayList<Prueba>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaPrueba + " ORDER BY id DESC LIMIT " + limite
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            // pstmt.setInt(1, 30000)
            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                var prueba = Prueba(ConexionEstatica.registros!!.getInt("id"), ConexionEstatica.registros!!.getString("tipo"), ConexionEstatica.registros!!.getInt("esfuerzo"))
                pruebas!!.add(prueba)
            }


        } catch (ex: SQLException) {
            println(ex)

        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return pruebas
    }

    fun insertPrueba(tipo: String, esfuerzo: Int): Int{
        var cod = 0
        val sentencia = ("INSERT INTO " + Constantes.TablaPrueba + " (tipo, esfuerzo) VALUES ('" + tipo + "'," + "'" + esfuerzo + "')")

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

    fun insertPruebas(arrPruebas:MutableList<Prueba>): Int{
        var cod = 0

        val sentencia = ("INSERT INTO " + Constantes.TablaPrueba + " (tipo, esfuerzo) VALUES (?,?)")
        var i = 0

            try {
                ConexionEstatica.abrirConexion()
                while (i<arrPruebas.size){
                val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
                pstmt.setString(1, arrPruebas[i].tipo )
                pstmt.setInt(2, arrPruebas[i].esfuerzo)
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

}