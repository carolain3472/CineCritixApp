package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

/*
data class PeliculasGeneroResponse(
    val peliculas: List<Pelicula>
)*/

data class PeliculasGeneroResponse(
    val id: Int,
    @SerializedName("imagen_pelicula") val imagenPelicula: String,
    @SerializedName("titulo_pelicula") val tituloPelicula: String,
    @SerializedName("director_pelicula") val directorPelicula: String,
    @SerializedName("sipnosis_pelicula") val sipnosisPelicula: String,
    @SerializedName("duracion_pelicula") val duracionPelicula: Int,
    @SerializedName("fecha_estreno_pelicula") val fechaEstrenoPelicula: String,
    @SerializedName("link_pelicula") val linkPelicula: String,
    @SerializedName("link_trailer") val linkTrailer: String,
    val genero: List<Int>,
    val actores: List<Int>
)