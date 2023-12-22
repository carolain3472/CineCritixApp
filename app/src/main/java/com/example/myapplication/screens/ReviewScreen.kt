package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.comentariosCallBack
import com.example.myapplication.data.viewModel.Comentario
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.RegistroViewModel
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
            HeadingTextComponentBlack(value = "REVIEWS")




        }
    }
}