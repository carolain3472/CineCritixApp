package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.MoviesCategoriesCallback
import com.example.myapplication.data.actoresCallBack
import com.example.myapplication.data.comentariosCallBack
import com.example.myapplication.data.request.PeliculaFavorita
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

data class listActores(
    var actorlist: MutableList<Actor> = mutableListOf()
)

data class listComentariosUser(
    var comentariolist: MutableList<Comentario> = mutableListOf()
)


data class Actor(
    var id: Int ,
    var imagenActor: String,
    var nombreActor: String,
    var nacimientoActor: String,
    var biografiaActor: String,
    var nacionalidadActor: String,
)

data class Comentario(
    var id: Int,
    var fechaComentario: String,
    var comentario: String,
    var userComentario: Int,
    var peliculaComentario: Int
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

data class MovieSelected(
    var id: Int=0,
    var imagenPelicula: String="",
    var tituloPelicula: String="",
    var directorPelicula: String="",
    var sipnosisPelicula: String="",
    var duracionPelicula: Int=0,
    var fechaEstrenoPelicula: String="",
    var linkPelicula: String="",
    var linkTrailer: String="",
    val genero: List<Int> = listOf(),
    val actores: List<Int> =listOf()
)

class MoviesSeriesViewModel : ViewModel() {

    val _uiStateFilter = mutableStateOf(infoFilter())
    val listMoviesFilter = mutableStateOf(listMoviesInfo())
    val listFavoriteMovie = mutableStateOf(listMoviesInfo())
    val movieSelected = mutableStateOf(MovieSelected())
    var listActores = mutableStateOf(listActores())
    var listComentarioUser = mutableStateOf(listComentariosUser())
    var listComentariosPelicula = mutableStateOf(listComentariosUser())


    var idUser = mutableStateOf(0)
    var idPeliculaComentario = mutableStateOf(0)


    var requestPeliculaFavorita = mutableStateOf(PeliculaFavorita())

    fun setidUser(id: Int){
        idUser.value = id
    }

    fun setidPeliculaComentario(id: Int){
        idPeliculaComentario.value = id
    }

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


    fun setMovieSelected(
        id:Int,
        imagenPelicula: String,
        tituloPelicula: String,
        directorPelicula: String,
        sipnosisPelicula: String,
        duracionPelicula: Int,
        fechaEstrenoPelicula: String,
        linkPelicula: String,
        linkTrailer: String,
        genero: List<Int>,
        actores: List<Int>

    ){
        movieSelected.value = movieSelected.value.copy(
            id=id,
            imagenPelicula=imagenPelicula,
            tituloPelicula=tituloPelicula,
            directorPelicula=directorPelicula,
            sipnosisPelicula=sipnosisPelicula,
            duracionPelicula=duracionPelicula,
            fechaEstrenoPelicula=fechaEstrenoPelicula,
            linkPelicula=linkPelicula,
            linkTrailer=linkTrailer,
            genero=genero,
            actores=actores

        )
    }

    fun getMovieSelected():MovieSelected {
        return movieSelected.value
    }

    fun setRequestPeliculaFavorita(pelicula:Int, user:Int, fecha:String){
        requestPeliculaFavorita.value = requestPeliculaFavorita.value.copy(
            usuario=user,
            pelicula=pelicula,
            fecha=fecha
        )
    }

    fun setFavoriteMovies(list: MutableList<Movie>){
        listFavoriteMovie.value = listFavoriteMovie.value.copy(
            moviesList = list
        )
    }

    fun getFavoriteMovies():MutableList<Movie> {
        return listFavoriteMovie.value.moviesList
    }

    fun setActoresList(list: MutableList<Actor>){
        listActores.value = listActores.value.copy(
            actorlist = list
        )
    }

    fun getActoresList():MutableList<Actor> {
        return listActores.value.actorlist
    }

    fun setComentariosList(list: MutableList<Comentario>){
        listComentarioUser.value = listComentarioUser.value.copy(
            comentariolist = list
        )
    }

    fun getComentariosList():MutableList<Comentario> {
        return listComentarioUser.value.comentariolist
    }

    fun setComentariosPeliculaList(list: MutableList<Comentario>){
        listComentariosPelicula.value = listComentariosPelicula.value.copy(
            comentariolist = list
        )
    }

    fun getComentariosPeliculaList():MutableList<Comentario> {
        return listComentariosPelicula.value.comentariolist
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


    fun setPeliculaFavorita(){



        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.agregarPeliculaFavorita(requestPeliculaFavorita.value)

                Log.d(TAG5, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG5, response.body().toString())


                }else{
                    Log.d(TAG5, "Error en la: ${response.code()}")
                }
            }catch (e:Exception){
                Log.d(TAG5, "Error en la carga: ${e.message}")
            }
        }
    }

    fun getPeliculasFavoritas(callBackInfoUser: MoviesCategoriesCallback){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getPeliculasFavoritas(usuario_id = idUser.value)

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


    fun getActoresPelicula(callBackActores: actoresCallBack){

        Log.d(TAG5, getMovieSelected().id.toString())


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getActoresPeliculas(pelicula_id = getMovieSelected().id)

                Log.d(TAG5, response.code().toString())

                if (response.isSuccessful) {
                    //Log.d(TAG5, response.body().toString())


                    withContext(Dispatchers.Main) {
                        var actores: MutableList<Actor> = mutableListOf()
                        response.body()?.forEach { actor ->
                            var actor = mutableStateOf(
                                Actor(
                                    id = actor.id,
                                    imagenActor = actor.imagenActor,
                                    nombreActor= actor.nombreActor,
                                    nacimientoActor= actor.nacimientoActor,
                                    biografiaActor= actor.biografiaActor,
                                    nacionalidadActor= actor.nacionalidadActor,
                                )
                            )
                            actores.add(actor.value)
                        }
                        callBackActores.onActoresResult(actores)
                    }

                }else{
                    Log.d(TAG5, "Error en la: ${response.code()}")
                }
            }catch (e:Exception){
                Log.d(TAG5, "Error en la carga: ${e.message}")
            }
        }

    }

    fun getPeliculas(callBackInfoUser: MoviesCategoriesCallback){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getPeliculas()

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

    fun getComentariosUser(callBackComentario: comentariosCallBack){

        Log.d(TAG5, idUser.value.toString())


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getComentarios(usuario_id = idUser.value)

                Log.d(TAG5, response.code().toString())

                if (response.isSuccessful) {
                    //Log.d(TAG5, response.body().toString())


                    withContext(Dispatchers.Main) {
                        var comentarios: MutableList<Comentario> = mutableListOf()
                        response.body()?.forEach { coment ->
                            var comentario = mutableStateOf(
                                Comentario(
                                    id = coment.id,
                                    fechaComentario=coment.fechaComentario,
                                    comentario = coment.comentario,
                                    peliculaComentario = coment.peliculaComentario,
                                    userComentario = coment.userComentario

                                )
                            )
                            comentarios.add(comentario.value)
                        }
                        callBackComentario.onComentariosResult(comentarios)
                    }

                }else{
                    Log.d(TAG5, "Error en la: ${response.code()}")
                }
            }catch (e:Exception){
                Log.d(TAG5, "Error en la carga: ${e.message}")
            }
        }

    }

    fun getComentariosPelicula(callBackComentario: comentariosCallBack){

        Log.d(TAG5, idUser.value.toString())


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getComentariosPelicula(pelicula_id = getMovieSelected().id )

                Log.d(TAG5, response.code().toString())

                if (response.isSuccessful) {
                    //Log.d(TAG5, response.body().toString())


                    withContext(Dispatchers.Main) {
                        var comentarios: MutableList<Comentario> = mutableListOf()
                        response.body()?.forEach { coment ->
                            var comentario = mutableStateOf(
                                Comentario(
                                    id = coment.id,
                                    fechaComentario=coment.fechaComentario,
                                    comentario = coment.comentario,
                                    peliculaComentario = coment.peliculaComentario,
                                    userComentario = coment.userComentario

                                )
                            )
                            comentarios.add(comentario.value)
                        }
                        callBackComentario.onComentariosResult(comentarios)
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