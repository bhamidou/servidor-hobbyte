package com.badr.models.UserModel

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(var username: String, var password: String)