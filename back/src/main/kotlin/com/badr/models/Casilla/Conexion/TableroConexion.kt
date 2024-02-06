package com.badr.models.Tablero.Conexion

import com.badr.models.Tablero.Tablero
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object TableroConexion {

    fun getTableros():ArrayList<Tablero>{
        val arrTablero = ArrayList<Tablero>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.tablaCasilla
            ConexionEstatica.registros = ConexionEstatica.sentenciaSQL!!.executeQuery(sentencia)
            while (ConexionEstatica.registros!!.next()) {
                arrTablero.add(
                    Tablero(
                        ConexionEstatica.registros!!.getInt("id_partida"),
                        ConexionEstatica.registros!!.getInt("id_prueba"),
                        ConexionEstatica.registros!!.getInt("estado")
                    )
                )
            }
        } catch (ex: SQLException) {
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return arrTablero
    }

    fun getTableroById(id:String?): Tablero?{
        var tablero:Tablero? = null
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.tablaCasilla + " where id = ?"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setString(1, id)
            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                tablero = Tablero( ConexionEstatica.registros!!.getInt("id_partida"), ConexionEstatica.registros!!.getInt("id_prueba"),
                    ConexionEstatica.registros!!.getInt("estado"))
            }
        } catch (ex: SQLException) {
            println(ex)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return tablero
    }


//    fun insertArr(arrTablero:ArrayList<Tablero>): Int{
//        var cod = 0
//
//        val sentencia = ("INSERT INTO " + Constantes.TablaPrueba + " (id_prueba, id_prueba, estado) VALUES (?,?,?)")
//        var i = 0
//
//        try {
//            while (i<arrTablero.size){
//                ConexionEstatica.abrirConexion()
//                val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
//                pstmt.setInt(1, arrTablero[i].id_prueba)
//                pstmt.setInt(2, arrTablero[i].id_prueba )
//                pstmt.setInt(3, arrTablero[i].estado)
//                pstmt.executeUpdate()
//                i++
//            }
//        } catch (sq: SQLException) {
//            cod = sq.errorCode
//            println(sq)
//        } finally {
//            ConexionEstatica.cerrarConexion()
//        }
//        return cod
//    }
}