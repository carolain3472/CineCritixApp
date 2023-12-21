package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.MoviesCategoriesCallback
import com.example.myapplication.data.request.UserInfo
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.checkerframework.checker.index.qual.LengthOf

var TAG5 = "MOVIESSERIES"

data class infoFilter (
        var textFilter: String = "",
        var idGenero: Int=0
)

data class listMoviesInfo(
    var moviesList: MutableList<Movie> = mutableListOf()
)


data class Movie(
    var id: Int ,
    var imagenPelicula: String,
    var tituloPelicula: String,
    var directorPelicula: String,
    var sipnosisPelicula: String,
    var duracionPelicula: Int,
    var fechaEstrenoPelicula: String,
    var linkPelicula: String,
    var linkTrailer: String,
    val genero: List<Int>,
    val actores: List<Int>
)

class MoviesSeriesViewModel : ViewModel() {

    val _uiStateFilter = mutableStateOf(infoFilter())
    val listMoviesFilter = mutableStateOf(listMoviesInfo())

    fun setTextFilter(text: String){
        _uiStateFilter.value = _uiStateFilter.value.copy(
            textFilter = text
        )
    }

    fun getTextFilter():String {
        return _uiStateFilter.value.textFilter
    }

    fun setIdGenero(id: Int){
        _uiStateFilter.value = _uiStateFilter.value.copy(
            idGenero = id
        )
    }

    fun getIdGenero():Int {
        return _uiStateFilter.value.idGenero
    }

    fun setMoviesListFilter(list: MutableList<Movie>){
        listMoviesFilter.value = listMoviesFilter.value.copy(
            moviesList = list
        )
    }

    fun getMoviesListFilter():MutableList<Movie> {
        return listMoviesFilter.value.moviesList
    }

    fun getPeliculasCategoria(callBackInfoUser: MoviesCategoriesCallback){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.filtrarPeliculasGenero(genero_id = getIdGenero())

                Log.d(TAG5, response.code().toString())
                if (response.isSuccessful) {
                    //Log.d(TAG5, response.body().toString())

                    withContext(Dispatchers.Main) {

                        var movies: MutableList<Movie> = mutableListOf()
                        response.body()?.forEach { movie ->

                            var movie = mutableStateOf(
                                Movie(
                                    id = movie.id,
                                    imagenPelicula = movie.imagenPelicula,
                                    tituloPelicula = movie.tituloPelicula,
                                    directorPelicula = movie.directorPelicula,
                                    sipnosisPelicula = movie.sipnosisPelicula,
                                    duracionPelicula = movie.duracionPelicula,
                                    fechaEstrenoPelicula = movie.fechaEstrenoPelicula,
                                    linkPelicula = movie.linkPelicula,
                                    linkTrailer = movie.linkTrailer,
                                    genero = movie.genero,
                                    actores = movie.actores
                                )
                            )

                            movies.add(movie.value)

                        }
                        callBackInfoUser.onMovieResult(movies)

                    }



                }else{
                    Log.d(TAG5, "Error en la: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG5, "Error en la carga: ${e.message}")

            }

        }
    }

}