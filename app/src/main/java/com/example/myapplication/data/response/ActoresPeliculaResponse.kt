package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class ActoresPeliculaResponse(

    val id: Int,
    @SerializedName("imagen_actor") val imagenActor: String,
    @SerializedName("nombre_actor") val nombreActor: String,
    @SerializedName("fecha_nacimiento") val nacimientoActor: String,
    @SerializedName("biografia") val biografiaActor: String,
    @SerializedName("nacionalidad") val nacionalidadActor: String,

)

