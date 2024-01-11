package com.badr.models.UserModel

import kotlinx.serialization.Serializable

@Serializable
data class User(var userId: Int, var username: String, var password: String)