package com.example.myapplication.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.myapplication.R
import com.example.myapplication.components.GradientButton
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.actoresCallBack
import com.example.myapplication.data.comentariosCallBack
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.viewModel.Actor
import com.example.myapplication.data.viewModel.Comentario
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.TAG5
import com.example.myapplication.data.viewModel.UserViewModel
import retrofit2.Response


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieInfoScreen(navController: NavHostController = rememberNavController(), moviesSeriesViewModel: MoviesSeriesViewModel = viewModel(), userViewModel: UserViewModel = viewModel() ){

    var id = userViewModel.userInfoResponse.value?.body()?.user_id
    var comentario by remember {
        mutableStateOf("")
    }

    userViewModel.getInfo(object : CallBackInfoUser {

        override fun onInfoResult(success: Response<UserInfoResponse>) {
            id = success.body()?.user_id

        }

    })

    moviesSeriesViewModel.getComentariosPelicula(
        object: comentariosCallBack {
            override fun onComentariosResult(success: MutableList<Comentario>) {
                moviesSeriesViewModel.setComentariosPeliculaList(success)
            }

        }
    )

    var comentarios by remember { mutableStateOf(moviesSeriesViewModel.getComentariosPeliculaList()) }

    Scaffold(
        topBar = {

            /**IconButton(onClick = { navController.navigate(BottomBarScreen.SearchFilter.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.salir)
                )
            }*/
            HeadingTextComponentBlack(value = moviesSeriesViewModel.getMovieSelected().tituloPelicula)
        },

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    )
    { contentPadding ->


        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            item{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    ) {
                        val borderColor = Color.Black // Color del borde
                        val borderWidth = 2.dp // Ancho del borde

                        Box(
                            modifier = Modifier
                                .size(200.dp) // Tamaño por defecto para la imagen
                                .border(borderWidth, borderColor)
                                .clip(shape = RoundedCornerShape(4.dp)) // Opcional: dar esquinas redondeadas
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(moviesSeriesViewModel.getMovieSelected().imagenPelicula)
                                    .crossfade(true)
                                    .scale(Scale.FILL)
                                    .build(),
                                contentDescription = "Imagen de película",
                                modifier = Modifier.fillMaxSize(),
                                placeholder = painterResource(id = R.drawable.placeholder),
                                error = painterResource(id = R.drawable.error)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = moviesSeriesViewModel.getMovieSelected().sipnosisPelicula,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp,
                            color = Color.Black // Establecer el color del texto
                        )
                    }
                }

                val movieGenres = moviesSeriesViewModel.getMovieSelected().genero
                val genreMap = mapOf(
                    1 to "Ciencia Ficción",
                    2 to "Aventura",
                    3 to "Acción",
                    4 to "Romance",
                    5 to "Fantasía",
                    6 to "Terror",
                    7 to "Suspenso",
                    8 to "Drama",
                    9 to "Comedia",
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    movieGenres.forEach { genreId ->
                        val genreName = genreMap[genreId] ?: "Desconocido"
                        ClickableText(
                            text = AnnotatedString(genreName),
                            modifier = Modifier
                                .background(Color.LightGray)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre los géneros
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))

                val listaActores = moviesSeriesViewModel.getActoresList()

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    listaActores.forEach { actor ->
                        val actorName = actor.nombreActor ?: "Desconocido"
                        Text(
                            text = actorName,
                            maxLines = 1, // Limita el texto a una línea
                            overflow = TextOverflow.Ellipsis, // Agrega puntos suspensivos (...) si el texto es demasiado largo
                            modifier = Modifier
                                .background(Color.LightGray)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .widthIn(max = 200.dp) // Puedes ajustar el ancho máximo aquí
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre los actores
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))

                GradientButton {
                    id?.let { it1 ->

                        moviesSeriesViewModel.setRequestPeliculaFavorita(
                            pelicula = moviesSeriesViewModel.getMovieSelected().id,
                            user = it1,
                            fecha = "2023-09-09"
                        )
                    }
                    moviesSeriesViewModel.setPeliculaFavorita()
                }

                HeadingTextComponentBlack(value = "Comentarios")

                Spacer(modifier = Modifier.size(15.dp))

                Text(text="Añadir un comentario")

                Spacer(modifier = Modifier.size(15.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){

                    OutlinedTextField(
                        value = comentario  ,
                        onValueChange = {comentario = it},
                        modifier = Modifier.height(60.dp).width(250.dp)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    GradientButton(text= "Enviar", {

                        id?.let { moviesSeriesViewModel.setRequestAddComentario( usuario= it, pelicula = moviesSeriesViewModel.getMovieSelected().id, comentario= comentario,   fecha = "2023-12-22") }
                        moviesSeriesViewModel.setComentario()
                        comentario = ""

                    })

                }

                val listaComentarios = moviesSeriesViewModel.getComentariosPeliculaList()

                Log.d(TAG5, "COMENTARIOS: " +comentarios.toString())

                Column(modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start

                ) {
                    listaComentarios.forEach { coment ->
                        
                        Text(text = coment.fechaComentario)
                        
                        Text(
                            text = coment.comentario,
                            overflow = TextOverflow.Ellipsis, // Agrega puntos suspensivos (...) si el texto es demasiado largo
                            modifier = Modifier
                                .background(Color.LightGray)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .widthIn(max = 200.dp) // Puedes ajustar el ancho máximo aquí
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre los actores
                    }
                }





                moviesSeriesViewModel.getActoresPelicula(
                    object: actoresCallBack {
                        override fun onActoresResult(success: MutableList<Actor>) {
                            moviesSeriesViewModel.setActoresList(success)
                            Log.d(TAG5, "Lista de actores:"+ moviesSeriesViewModel.getActoresList().toString())
                        }

                    }
                )

            }

        }

    }

}
