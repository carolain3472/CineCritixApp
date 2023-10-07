package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun TerminosCondicionesScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {

        HeadingTextComponentBlack(value = stringResource(id = R.string.terminosCondiciones))
        SystemBackButtonHandler{
            CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
        }

    }
}

@Preview
@Composable
fun TerminosCondicionesScreenPreview(){
    TerminosCondicionesScreen()
}