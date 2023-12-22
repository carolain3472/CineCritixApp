package com.example.myapplication.data.request

data class agregarComentarioPelicula(
    var usuario: Int = 0,
    var pelicula: Int = 0,
    var fecha: String = "",
    var comentario: String = ""
)
