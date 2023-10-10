package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.CardSlider
import com.example.myapplication.components.LoginLandInButtonComponent
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen


@Composable
fun LandInScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Agregar un icono de usuario (puedes cambiar el recurso a tu propio icono)

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Usuario",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp) // Añade un pequeño relleno para evitar que el color se corte
                            .background(Color.White, shape = CircleShape) // Cambia el color del fondo a blanco
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // Agregar un botón para iniciar sesión y redirigir
                    LoginLandInButtonComponent(
                        text = "Iniciar Sesión",
                        onClick = {
                            CineCritixAppRouter.navigateTo(Screen.Login)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                // Agregar el logo
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "Logo"
                )

                Spacer(modifier = Modifier.height(20.dp))

                CardSlider()
            }
        }
    }
}


@Preview
@Composable
fun LandInScreenPreview(){
    LandInScreen()
}