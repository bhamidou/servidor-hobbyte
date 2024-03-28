package com.badr.service

import com.badr.models.UserModel.Conexion.ConexionUser
import com.badr.models.UserModel.User

object ServiceUser {

    fun getAll():MutableList<User>{
        return ConexionUser.getAll()
    }
    fun login(email: String?, password: String?): User?{
        return ConexionUser.login(email, password)
    }
    fun updateCodeUser(email: String, confirmation:String): Int{
        return ConexionUser.updateCodeUser(email, confirmation)
    }
    fun insertarUser(nombre: String, email: String,password: String, confirmation:String): Int{
        var newUser = User(email, nombre,password, confirmation)
        return ConexionUser.insertarUser(newUser)
    }

    fun getUserByEmailAndCode(email:String, confirmation:String):Int{
        var searchUser = User(email, confirmation)
        return ConexionUser.getUserByEmailAndCode(searchUser)
    }

    fun getUserByEmail(email:String):User{
        return ConexionUser.getUserByEmail(email)
    }

    fun deleteUserById(id_user: String):Int{
        return ConexionUser.deleteUser(id_user)
    }


}