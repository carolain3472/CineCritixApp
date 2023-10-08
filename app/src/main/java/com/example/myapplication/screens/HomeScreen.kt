package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.RegistroViewModel

@Composable
fun HomeScreen(loginViewModel: RegistroViewModel = viewModel()){
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
            HeadingTextComponentBlack(value = "HOME")
            
            ButtonComponent(value = "Logout",
                onButtonClicked = {
                loginViewModel.logout() },
                isEnabled = true)

        }
}}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}