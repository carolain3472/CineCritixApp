package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.MovieCallBack
import com.example.myapplication.data.comentariosCallBack
import com.example.myapplication.data.viewModel.Comentario
import com.example.myapplication.data.viewModel.MovieSelected
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.TAG5
import com.example.myapplication.data.viewModel.UserViewModel

@Composable
fun ReviewScreen(navController: NavHostController, moviesSeriesViewModel: MoviesSeriesViewModel = viewModel(), userViewModel: UserViewModel = viewModel()){


    var id = userViewModel.userInfoResponse.value?.body()?.user_id

    Log.d(TAG5, id.toString())

    id?.let { moviesSeriesViewModel.setidUser(it) }

    moviesSeriesViewModel.getComentariosUser(
        object : comentariosCallBack{
            override fun onComentariosResult(success: MutableList<Comentario>) {
                moviesSeriesViewModel.setComentariosList(success)
            }

        }
    )

    var comentariosUser by remember { mutableStateOf(moviesSeriesViewModel.getComentariosList()) }

    Log.d(TAG5, comentariosUser.toString())


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ){
            HeadingTextComponentBlack(value = "COMENTARIOS")

            LazyColumn(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){item{
                val listaComentarios = moviesSeriesViewModel.getComentariosList()

                listaComentarios.forEach { coment ->

                    //var foto = ""

                    moviesSeriesViewModel.getDatosPelicula( coment.peliculaComentario,
                        object: MovieCallBack{
                            override fun onMovieResult(success: MovieSelected) {
                                //foto = success.value?.imagenPelicula ?: ""
                                moviesSeriesViewModel.setInfoMovie(success)

                            }

                        }
                    )

                    cardComentario(
                        imagen = "",
                        comentario = coment.comentario,
                        tituloPelicula = moviesSeriesViewModel.infoMovie.value.tituloPelicula ,
                        pelicula = coment.peliculaComentario ,
                        fecha = coment.fechaComentario
                    )

                    Spacer(modifier = Modifier.size(20.dp))





                }
            }




            }



        }
    }
}

@Composable
fun cardComentario(
    imagen:String,
    comentario : String,
    tituloPelicula:String,
    pelicula:Int,
    fecha: String,
    moviesSeriesViewModel: MoviesSeriesViewModel = viewModel(),

){

    Surface(modifier= Modifier
        .background(Color.LightGray)
        .shadow(8.dp, shape = MaterialTheme.shapes.medium)
        .fillMaxWidth()


    ) {

        Column (modifier = Modifier
            .background(Color.LightGray)
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ){

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){

                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "borrar"
                    )
                }

                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "editar"
                    )
                }
            }



            Text(text = fecha)

            Text(text = tituloPelicula)

            Text(
                text = comentario,
                overflow = TextOverflow.Ellipsis, // Agrega puntos suspensivos (...) si el texto es demasiado largo
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .widthIn(max = 200.dp) // Puedes ajustar el ancho máximo aquí
            )

            Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre los actores



        }

    }





}


