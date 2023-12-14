package com.badr.models

import kotlinx.serialization.Serializable

@Serializable
data class Respuesta(val message:String, val status:Int)
