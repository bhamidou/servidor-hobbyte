package com.badr.models.Heroes.Conexion

import com.badr.models.Heroes.Heroe
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object HeroeConexion {
    fun getActivedHeroes():MutableList<Heroe>{
        val arrHeroe = ArrayList<Heroe>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.tablaHeroe + " where activo = true"
            ConexionEstatica.registros = ConexionEstatica.sentenciaSQL!!.executeQuery(sentencia)
            while (ConexionEstatica.registros!!.next()) {
                arrHeroe.add(
                    Heroe(
                        ConexionEstatica.registros!!.getInt("id"),
                        ConexionEstatica.registros!!.getString("nombre"),
                        ConexionEstatica.registros!!.getString("tipo_prueba")
                    )
                )
            }
        } catch (ex: SQLException) {
            println(ex)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return arrHeroe
    }

    fun getHeroById(id_heroe:Int):Heroe{
        var heroe = Heroe()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.tablaHeroe + " where id = " + id_heroe
            ConexionEstatica.registros = ConexionEstatica.sentenciaSQL!!.executeQuery(sentencia)

            if(ConexionEstatica.registros!!.next()) {
                heroe = Heroe(
                        ConexionEstatica.registros!!.getInt("id"),
                        ConexionEstatica.registros!!.getString("nombre"),
                        ConexionEstatica.registros!!.getString("tipo_prueba")
                )
            }
        } catch (ex: SQLException) {
            println(ex)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return heroe
    }

    fun insertArrHeroes(arrTablero:MutableList<Heroe>): Int{
        var cod = 0

        val sentencia = ("INSERT INTO " + Constantes.tablaHeroe + " (nombre, tipo_prueba, activo) VALUES (?,?, ?)")
        var i = 0

        try {
            while (i<arrTablero.size){
                ConexionEstatica.abrirConexion()
                val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
                pstmt.setString(1, arrTablero[i].nombre)
                pstmt.setString(2, arrTablero[i].tipo_prueba)
                pstmt.setBoolean(3, arrTablero[i].activo)
                pstmt.executeUpdate()
                i++
            }
            cod = 1
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }

}