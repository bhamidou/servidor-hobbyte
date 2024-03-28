package com.badr.models.HeroePartida.Conexion

import com.badr.models.HeroePartida.HeroePartida
import com.badr.models.Heroes.Heroe
import com.badr.models.Prueba.Prueba
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object HeroePartidaConexion {
    fun insertArrHeroeUser(arrTablero:MutableList<HeroePartida>): Int{
        var cod = 0

        val sentencia = ("INSERT INTO " + Constantes.TablaHeroePartida + " (id_heroe, id_partida, capacidad) VALUES (?,?,?)")
        var i = 0

        try {
            while (i<arrTablero.size){
                ConexionEstatica.abrirConexion()
                val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
                pstmt.setInt(1, arrTablero[i].id_heroe)
                pstmt.setInt(2, arrTablero[i].id_partida)
                pstmt.setInt(3, arrTablero[i].capacidad)
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

    fun getArrHeroeUserByPartida(id_partida:Int): MutableList<HeroePartida>{
        val arrHeroePartida = ArrayList<HeroePartida>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaHeroePartida + " where  id_partida = " + id_partida
            ConexionEstatica.registros = ConexionEstatica.sentenciaSQL!!.executeQuery(sentencia)
            while (ConexionEstatica.registros!!.next()) {
                arrHeroePartida.add(
                    HeroePartida(
                        ConexionEstatica.registros!!.getInt("id_partida"),
                        ConexionEstatica.registros!!.getInt("id_heroe"),
                        ConexionEstatica.registros!!.getInt("capacidad")
                    )
                )
            }
        } catch (ex: SQLException) {
            println(ex)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return arrHeroePartida
    }

    fun getHeroeUserByPartida(id_partida:Int, id_heroe:Int): HeroePartida{
        var heroePartida= HeroePartida()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaHeroePartida + " where  id_partida = " + id_partida + " and id_heroe = " + id_heroe
            ConexionEstatica.registros = ConexionEstatica.sentenciaSQL!!.executeQuery(sentencia)
            while (ConexionEstatica.registros!!.next()) {
                heroePartida =
                    HeroePartida(
                        ConexionEstatica.registros!!.getInt("id_partida"),
                        ConexionEstatica.registros!!.getInt("id_heroe"),
                        ConexionEstatica.registros!!.getInt("capacidad")
                    )

            }
        } catch (ex: SQLException) {
            println(ex)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return heroePartida
    }

    fun updateHeroe(heroePartida: HeroePartida): Int {
        var cod = 0

        val sentencia =
            ("UPDATE " + Constantes.TablaHeroePartida + " SET capacidad = ? where id_partida = ? and id_heroe = ?" )

        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setInt(1, heroePartida.capacidad)
            pstmt.setInt(2,heroePartida.id_partida)
            pstmt.setInt(3, heroePartida.id_heroe)

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