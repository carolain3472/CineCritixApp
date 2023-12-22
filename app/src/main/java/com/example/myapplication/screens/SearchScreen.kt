package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.data.LoginCallBack
import com.example.myapplication.data.MoviesCategoriesCallback
import com.example.myapplication.data.viewModel.Movie
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.TAG5


@Composable
fun SearchScreen( navController: NavHostController = rememberNavController(),  moviesSeriesViewModel: MoviesSeriesViewModel = viewModel()){

    var search by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()
        .background(Color.White),
        contentAlignment = Alignment.Center
        ) {
        Surface(modifier = Modifier
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .background(Color.Black)
            .width(360.dp),
            contentColor = Color.Black
        ) {

            LazyColumn(modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(8.dp),
                horizontalAlignment =  Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {

                items(1) {
                    Box ( modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){
                        IconButton(
                            onClick = {navController.navigate(BottomBarScreen.Home.route)}) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Salir",
                                tint = colorResource(id = R.color.titulocard)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(30.dp))

                    Image(
                        painter = painterResource(id = R.drawable.icono),
                        modifier = Modifier
                            .width(300.dp)
                            .height(100.dp),
                        contentDescription = null)

                    Spacer(modifier = Modifier.size(30.dp))




                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .shadow(8.dp, shape = androidx.compose.material.MaterialTheme.shapes.medium),
                        horizontalArrangement = Arrangement.Center,
                        ){

                        OutlinedTextField(
                            value = search ,
                            onValueChange = { search = it;/** boolEdit= true*/ },
                            leadingIcon = {  },
                            modifier = Modifier
                                .padding(bottom = 15.dp)
                                .height(48.dp)
                                .width(250.dp)
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.sombraBoton),
                                    shape = RoundedCornerShape(8.dp))
                                .background(Color.Black),

                            textStyle = TextStyle.Default.copy(color = Color.White)

                        )

                        IconButton(
                            modifier = Modifier
                                .background(colorResource(id = R.color.buscarcard)),
                            onClick = {

                                moviesSeriesViewModel.setTextFilter("Todas las peliculas")
                                //3,7
                                moviesSeriesViewModel.getPeliculas(
                                    object : MoviesCategoriesCallback {
                                        override fun onMovieResult(success: MutableList<Movie>) {
                                            moviesSeriesViewModel.setMoviesListFilter(success)
                                            Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                        }

                                    }
                                )

                                moviesSeriesViewModel.getPeliculasFavoritas(
                                    object : MoviesCategoriesCallback {
                                        override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                            moviesSeriesViewModel.setFavoriteMovies(success)
                                            //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                        }

                                    }
                                )
                                navController.navigate(BottomBarScreen.SearchFilter.route)


                            }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = stringResource(id = BottomBarScreen.Search.title)
                            )
                        }
                    }

                    ButtonCategories("Acción", navController, onClick = {
                        moviesSeriesViewModel.setTextFilter("Acción")
                        moviesSeriesViewModel.setIdGenero(3)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)
                    })

                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Aventura", navController, onClick = {
                        moviesSeriesViewModel.setTextFilter("Aventura")
                        moviesSeriesViewModel.setIdGenero(2)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)

                    })
                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Romance", navController, onClick = {

                        moviesSeriesViewModel.setTextFilter("Romance")
                        moviesSeriesViewModel.setIdGenero(4)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)
                    })
                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Comedia", navController, onClick = {

                        moviesSeriesViewModel.setTextFilter("Comedia")
                        moviesSeriesViewModel.setIdGenero(9)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)

                    })
                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Ciencia Ficción", navController, onClick = {

                        moviesSeriesViewModel.setTextFilter("Ciencia Ficción")
                        moviesSeriesViewModel.setIdGenero(1)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)
                    })
                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Fantasía", navController, onClick = {

                        moviesSeriesViewModel.setTextFilter("Fantasía")
                        moviesSeriesViewModel.setIdGenero(5)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)

                    })
                    Spacer(modifier = Modifier.size(10.dp))

                    ButtonCategories("Drama", navController, onClick = {
                        moviesSeriesViewModel.setTextFilter("Drama")
                        moviesSeriesViewModel.setIdGenero(8)
                        //3,7
                        moviesSeriesViewModel.getPeliculasCategoria(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<Movie>) {
                                    moviesSeriesViewModel.setMoviesListFilter(success)
                                    Log.d(TAG5, moviesSeriesViewModel.getMoviesListFilter().toString() )
                                }

                            }
                        )

                        moviesSeriesViewModel.getPeliculasFavoritas(
                            object : MoviesCategoriesCallback {
                                override fun onMovieResult(success: MutableList<com.example.myapplication.data.viewModel.Movie>) {
                                    moviesSeriesViewModel.setFavoriteMovies(success)
                                    //Log.d(TAG5, moviesSeriesViewModel.getFavoriteMovies().toString() )
                                }

                            }
                        )
                        navController.navigate(BottomBarScreen.SearchFilter.route)
                    })

                    Spacer(modifier = Modifier.size(30.dp))
                }


            }


        }


    }


}


@Composable
fun ButtonCategories(categoria: String , navController: NavHostController, onClick: () -> Unit){

    Button(
        modifier = Modifier
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .width(300.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.sombraBoton),
                shape = RoundedCornerShape(12.dp)
            ),
        colors = ButtonDefaults.buttonColors(Color.Black),
        onClick = { onClick.invoke() }) {
        Text(text = categoria,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray)

    }

}

@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen()
}
