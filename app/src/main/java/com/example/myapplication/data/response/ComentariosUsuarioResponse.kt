package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class ComentariosUsuarioResponse(
    val id: Int,
    @SerializedName("fecha") val fechaComentario: String,
    @SerializedName("comentario") val comentario: String,
    @SerializedName("usuario") val userComentario: Int,
    @SerializedName("pelicula") val peliculaComentario: Int,
)
