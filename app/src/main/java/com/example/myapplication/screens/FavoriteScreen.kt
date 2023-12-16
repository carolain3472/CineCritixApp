package com.example.myapplication.screens

import android.graphics.Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.components.CardSlider
import com.example.myapplication.components.ClickeableTextComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.components.movieCard
import com.example.myapplication.components.movieCardFavoritas
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.google.firebase.inappmessaging.model.Button

@Composable
fun FavoriteScreen(navController: NavHostController, loginViewModel: RegistroViewModel = viewModel()) {

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
            item{

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

                    Row (modifier = Modifier.fillMaxWidth()
                        , verticalAlignment = Alignment.CenterVertically
                        , horizontalArrangement = Arrangement.Center){
                        movieCardFavoritas(R.drawable.joker, {}, "Joker") {
                            onCardClick("Joker", R.drawable.joker, "Descripción de Joker")
                        }
                        Spacer(modifier = Modifier.size(5.dp))
                        movieCardFavoritas(R.drawable.backtothefuture, {}, "Back to the Future") {
                            onCardClick("Back to the Future", R.drawable.backtothefuture, "Descripción de Back to the Future")
                        }

                    }

                    Spacer(modifier = Modifier.size(15.dp))

                    Row (modifier = Modifier.fillMaxWidth()
                        , verticalAlignment = Alignment.CenterVertically
                        , horizontalArrangement = Arrangement.Center){
                        movieCardFavoritas(R.drawable.endgame, {}, "Avengers: End Game") {
                            onCardClick("Avengers: End Game", R.drawable.endgame, "Descripción de Avengers: End Game")
                        }
                        Spacer(modifier = Modifier.size(5.dp))
                        movieCardFavoritas(R.drawable.titanic, {}, "Titanic") {
                            onCardClick("Titanic", R.drawable.titanic, "Descripción de Titanic")
                        }

                    }

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
                                        Image(
                                            painter = painterResource(id = selectedMovieImage),
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Text(text = selectedMovieDescription)
                                    // Aquí puedes agregar más detalles de la película como calificaciones, etc.
                                }
                            }
                        )
                    }

                }

            }
    }
}

@Composable
fun Box(modifier: Any, content: () -> Unit) {

}

