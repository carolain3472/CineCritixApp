package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.actoresCallBack
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.viewModel.Actor
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.TAG5
import com.example.myapplication.data.viewModel.UserViewModel
import retrofit2.Response

@Composable
fun MovieInfoScreen(navController: NavHostController = rememberNavController(), moviesSeriesViewModel: MoviesSeriesViewModel = viewModel(), userViewModel: UserViewModel = viewModel() ){

    var id = userViewModel.userInfoResponse.value?.body()?.user_id

    userViewModel.getInfo(object : CallBackInfoUser {

        override fun onInfoResult(success: Response<UserInfoResponse>) {
            id = success.body()?.user_id

        }

    })

    Scaffold(
        topBar = {

            /**IconButton(onClick = { navController.navigate(BottomBarScreen.SearchFilter.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.salir)
                )
            }*/
            HeadingTextComponentBlack(value = "Pelicula")
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

            items(1){
                Button(onClick={

                    id?.let { it1 ->
                        moviesSeriesViewModel.setRequestPeliculaFavorita(
                            pelicula = moviesSeriesViewModel.getMovieSelected().id,
                            user = it1,
                            fecha= "2023-09-09"
                        )
                    }

                    moviesSeriesViewModel.setPeliculaFavorita()

                }){

                    Text(text = "Añadir Favorito")

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
