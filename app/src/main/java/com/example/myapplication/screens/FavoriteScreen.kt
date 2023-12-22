package com.example.myapplication.screens

import android.graphics.Movie
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.myapplication.components.CardSlider
import com.example.myapplication.components.ClickeableTextComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.components.movieCard
import com.example.myapplication.components.movieCardFavoritas
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.MoviesCategoriesCallback
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.example.myapplication.data.viewModel.TAG5
import com.example.myapplication.data.viewModel.UserViewModel
import retrofit2.Response

//import com.google.firebase.inappmessaging.model.Button

@Composable
fun FavoriteScreen(navController: NavHostController = rememberNavController(), moviesSeriesViewModel: MoviesSeriesViewModel = viewModel(), userViewModel: UserViewModel = viewModel() ) {


    var id = userViewModel.userInfoResponse.value?.body()?.user_id

    Log.d(TAG5, id.toString())

    id?.let { moviesSeriesViewModel.setidUser(it) }
    id?.let { Log.d(TAG5, "idUser en el view Model"+moviesSeriesViewModel.idUser.toString()) }

    moviesSeriesViewModel.getPeliculasFavoritas(
        object : MoviesCategoriesCallback {
            override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                moviesSeriesViewModel.setFavoriteMovies(success)
                //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
            }

        }
    )

    var listMoviesFilter by remember { mutableStateOf(moviesSeriesViewModel.getFavoriteMovies()) }


    Log.d(TAG5, listMoviesFilter.toString())


    var showDetailsDialog by remember { mutableStateOf(false) }
    var selectedMovieTitle by remember { mutableStateOf("") }
    var selectedMovieImage: Int? = null
    var selectedMovieDescription by remember { mutableStateOf("") }

    val onCardClick: (String, Int, String) -> Unit = { title, image, description ->
        selectedMovieTitle = title
        selectedMovieImage = image
        selectedMovieDescription = description
        showDetailsDialog = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.size(15.dp))

        HeadingTextComponentBlack(value = "FAVORITAS")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){




            items(listMoviesFilter.chunked(2)) { rowImages ->

                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowImages.forEach { imageIndex ->

                        movieCardFavoritasNube(image = imageIndex.imagenPelicula,
                            titulo = imageIndex.tituloPelicula,
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

                            })

                        Spacer(modifier = Modifier.size(8.dp))

                    }
                }
                Spacer(modifier = Modifier.size(15.dp))


            }


            /**item{

            Column(
            modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            ){
            HeadingTextComponentBlack(value = "FAVORITAS")

            Spacer(modifier = Modifier.size(15.dp))

            Row (modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center){
            movieCardFavoritas(R.drawable.barbie, {}, "Barbie") {
            onCardClick("Barbie", R.drawable.barbie, "Descripción de Barbie")
            }
            Spacer(modifier = Modifier.size(5.dp))
            movieCardFavoritas(R.drawable.grinch, {}, "El grinch") {
            onCardClick("El grinch", R.drawable.grinch, "Descripción de El grinch")
            }

            }

            Spacer(modifier = Modifier.size(15.dp))

            Row (modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center){
            movieCardFavoritas(R.drawable.coco, {}, "Coco") {
            onCardClick("Coco", R.drawable.coco, "Descripción de Coco")
            }
            Spacer(modifier = Modifier.size(5.dp))
            movieCardFavoritas(R.drawable.matrix, {}, "Matrix") {
            onCardClick("Matrix", R.drawable.matrix, "Descripción de Matrix")
            }

            }

            Spacer(modifier = Modifier.size(15.dp))

            //ACA INICIA EL SHOW DIALOG

            if (showDetailsDialog) {
            AlertDialog(
            onDismissRequest = { showDetailsDialog = false },
            confirmButton = {
            Button(onClick = { showDetailsDialog = false }) {
            Text(text = "Cerrar")
            }
            },
            title = {
            Text(text = selectedMovieTitle)
            },
            text = {
            Column {
            Box(
            modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Establece la altura de la imagen
            .background(Color.LightGray)
            ) {

            /*
            Image(
            painter = painterResource(id = selectedMovieImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            )*/
            }

            Text(text = selectedMovieDescription)
            // Aquí puedes agregar más detalles de la película como calificaciones, etc.
            }
            }
            )
            }

            //ACA TERMINA EL SHOW DIALOG

            }

            }*/
        }

    }

}


@Composable
fun movieCardFavoritasNube(
    image: String,
    titulo: String,
    onCardClick: () -> Unit // Nuevo parámetro para manejar el clic en la tarjeta
){
    Surface(
        modifier = Modifier
            .clickable(onClick = { onCardClick.invoke() }) // Llamando a onCardClick al hacer clic en la tarjeta
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .width(150.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.colorPrimary))
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = "Imagen",
                modifier = Modifier
                    .height(200.dp) // Establecer una altura fija para las imágenes
                    .fillMaxWidth()
                    .shadow(8.dp, shape = MaterialTheme.shapes.medium),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.error)
            )

            Spacer(modifier = Modifier.size(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = titulo,
                    color = colorResource(id = R.color.black),
                    fontSize = 15.sp,
                    style = TextStyle(fontFamily = FontFamily.Serif),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}



@Composable
@Preview
fun preview(){

    FavoriteScreen()
}

