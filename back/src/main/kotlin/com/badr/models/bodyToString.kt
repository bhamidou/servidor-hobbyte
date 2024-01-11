package com.badr.models

import kotlinx.serialization.Serializable

@Serializable
data class BodyToString( var valores:Map<String,String>)