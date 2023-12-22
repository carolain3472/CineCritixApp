package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.comentariosCallBack
import com.example.myapplication.data.viewModel.Comentario
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel

@Composable
fun SearchFilterScreen( navController: NavHostController = rememberNavController(),  moviesSeriesViewModel: MoviesSeriesViewModel = viewModel()) {

    var filtro = moviesSeriesViewModel.getTextFilter()

    var listMoviesFilter = moviesSeriesViewModel.getMoviesListFilter()

    Scaffold(
        topBar = {
            IconButton(onClick = { navController.navigate(BottomBarScreen.Search.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.salir)
                )
            }
            HeadingTextComponentBlack(value = filtro)
        },

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    )
    { contentPadding ->


        LazyColumn(
            modifier = Modifier.padding(contentPadding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            items(listMoviesFilter.chunked(2)) { rowImages ->

                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowImages.forEach { imageIndex ->

                        movieInfo(imageIndex.tituloPelicula,
                            "Pelicula",
                            imageIndex.imagenPelicula,
                            {
                                navController.navigate(BottomBarScreen.MovieInfo.route)
                                moviesSeriesViewModel.setMovieSelected(
                                    id=imageIndex.id,
                                    imagenPelicula = imageIndex.imagenPelicula,
                                    tituloPelicula = imageIndex.tituloPelicula,
                                    directorPelicula = imageIndex.directorPelicula,
                                    sipnosisPelicula = imageIndex.sipnosisPelicula,
                                    duracionPelicula = imageIndex.duracionPelicula,
                                    fechaEstrenoPelicula = imageIndex.fechaEstrenoPelicula,
                                    linkPelicula = imageIndex.linkPelicula,
                                    linkTrailer = imageIndex.linkTrailer,
                                    genero = imageIndex.genero,
                                    actores = imageIndex.actores
                                )

                                moviesSeriesViewModel.getComentariosPelicula(
                                    object: comentariosCallBack{
                                        override fun onComentariosResult(success: MutableList<Comentario>) {
                                            moviesSeriesViewModel.setComentariosPeliculaList(success)
                                        }

                                    }
                                )

                            })

                    }
                }


            }

        }

    }
}



@Composable
fun movieInfo(titulo: String, tipo: String, urlImagen:String, onClick: () -> Unit){



    Surface(modifier = Modifier
        .padding(10.dp)
        .clickable(onClick = { onClick.invoke() })
        .shadow(8.dp, shape = MaterialTheme.shapes.medium)
        .width(150.dp)
        .height(250.dp)
    ){

        Column(modifier = Modifier

            .background(colorResource(id = R.color.titulocard))
            .padding(10.dp),// Tamaño del contenedor, puedes ajustarlo según tus necesidades,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Box(
                modifier = Modifier
                    //.size(120.dp)
                    .width(100.dp)
                    .height(160.dp)// Tamaño del contenedor, puedes ajustarlo según tus necesidades
                    //.border(3.dp, Color.White)
                    //.clip(RectangleShape)
                    //.border(4.dp, Color.White, RectangleShape)
            ){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(urlImagen)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.error)
                )

            }


            HeadingTextComponentBlack(value = titulo, size = 18.sp)

            Text(text = tipo)

        }
        
    }



}

@Composable
@Preview
fun SearchFilterScreenPreview(){
    SearchFilterScreen()
}