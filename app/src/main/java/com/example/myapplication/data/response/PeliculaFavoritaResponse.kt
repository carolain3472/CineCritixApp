package com.example.myapplication.data.response

data class PeliculaFavoritaResponse(
    var respuesta: infoPeliculaFavorita = infoPeliculaFavorita()
)

data class infoPeliculaFavorita(
    var id: Int = 1,
    var fecha:String = "2023-09-09",
    var usuario: Int = 25,
    var pelicula:Int= 1
)
