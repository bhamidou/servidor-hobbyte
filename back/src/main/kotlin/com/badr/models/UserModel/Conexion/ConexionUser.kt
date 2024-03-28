package com.badr.models.UserModel.Conexion

import com.badr.models.UserModel.User
import com.badr.utils.ConexionEstatica
import com.badr.utils.Constantes
import java.sql.SQLException

object ConexionUser {
    fun getAll(): MutableList<User> {
        var arrUser = ArrayList<User>()
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT id, nombre, email FROM " + Constantes.TablaUser
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)

            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                arrUser.add(User(
                    ConexionEstatica.registros!!.getInt("id"), ConexionEstatica.registros!!.getString("email"), ConexionEstatica.registros!!.getString("nombre")))
            }

        } catch (ex: SQLException) {
            println(ex)
        } finally {

            ConexionEstatica.cerrarConexion()
        }
        return arrUser
    }
    fun login(email: String?, password: String?): User? {
        var user: User? = null
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "SELECT * FROM " + Constantes.TablaUser + " WHERE email = ? AND password = ? and confirmation = 1"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            // pstmt.setInt(1, 30000);
            pstmt.setString(1, email)
            pstmt.setString(2, password)
            ConexionEstatica.registros = pstmt.executeQuery()
            while (ConexionEstatica.registros!!.next()) {
                user = User(
                    ConexionEstatica.registros!!.getInt("id"), ConexionEstatica.registros!!.getString("email"), ConexionEstatica.registros!!.getString("nombre") ,
                    ConexionEstatica.registros!!.getString("password"))
            }

        } catch (ex: SQLException) {
            println(ex)
        } finally {

            ConexionEstatica.cerrarConexion()
        }
        return user
    }


    // ----------------------------------------------------------
    fun updateCodeUser(email: String, confirmation:String): Int {
        var cod = 0
        try {
            ConexionEstatica.abrirConexion()
            val sentencia = "UPDATE " + Constantes.TablaUser + " SET confirmation = ? WHERE email = ?"
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setString(1, confirmation)
            pstmt.setString(2, email)
            pstmt.executeUpdate()
        } catch (ex: SQLException) {
            cod = ex.errorCode
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }
    // ----------------------------------------------------------
    fun insertarUser(user:User): Int {
        var cod = 0
        val sentencia = ("INSERT INTO " + Constantes.TablaUser + " (nombre, email, password, confirmation) VALUES (?,?,?,?)")
        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setString(1, user.nombre)
            pstmt.setString(2, user.email)
            pstmt.setString(3, user.password)
            pstmt.setString(4, user.confirmation)
            pstmt.executeUpdate()
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }

    fun getUserByEmailAndCode(user:User): Int {
        var cod = 0
        val sentencia = "SELECT * FROM " + Constantes.TablaUser + " WHERE email = ? AND confirmation = ?"
        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            // pstmt.setInt(1, 30000);
            pstmt.setString(1, user.email)
            pstmt.setString(2, user.confirmation)
            ConexionEstatica.registros = pstmt.executeQuery()

            if (ConexionEstatica.registros!!.next()) {
                var conf = ConexionEstatica.registros!!.getString("confirmation")
                if(conf != null){
                    cod = 1
                }
            }
        } catch (sq: SQLException) {
            cod = sq.errorCode
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return cod
    }

    fun getUserByEmail(email: String): User {
        var user = User()
        val sentencia = "SELECT * FROM " + Constantes.TablaUser + " WHERE email = ?"
        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            // pstmt.setInt(1, 30000);
            pstmt.setString(1, email)
            ConexionEstatica.registros = pstmt.executeQuery()

            if (ConexionEstatica.registros!!.next()) {
                user = User(
                    ConexionEstatica.registros!!.getInt("id"), ConexionEstatica.registros!!.getString("email"), ConexionEstatica.registros!!.getString("nombre") ,
                    ConexionEstatica.registros!!.getString("password"))
            }
        } catch (sq: SQLException) {
            println(sq)
        } finally {
            ConexionEstatica.cerrarConexion()
        }
        return user
    }

    fun deleteUser(id_user: String): Int {
        var cod = 0
        val sentencia = ("DELETE FROM " + Constantes.TablaUser + " where id = ?")
        try {
            ConexionEstatica.abrirConexion()
            val pstmt = ConexionEstatica.conexion!!.prepareStatement(sentencia)
            pstmt.setString(1, id_user)
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