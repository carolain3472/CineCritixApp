package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.components.CardSlider
import com.example.myapplication.components.LoginLandInButtonComponent
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.components.RegisterLandInButtonComponent


@Composable
fun LandInScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
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
                        text = stringResource(id = R.string.inciarLand),
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

                Text(
                    text = "En el emocionante mundo del cine y la " +
                            "televisión, cada experiencia es única." +
                            "Cada película y serie es una historia " +
                            "por descubrir, un viaje por realizar, y " +
                            "una emoción por experimentar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "¿Qué esperas para registrarte?",
                    color = colorResource(id = R.color.colorPrimary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

    }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Texto y botón a la izquierda
                    Column (
                        modifier = Modifier
                            .weight(2f)
                            .padding(12.dp)
                    ){
                        Text(
                            text = "Únete a CineCritix " +
                                    "y comparte tus " +
                                    "opiniones.",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Tu pasión, tus reseñas, tu comunidad.",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        RegisterLandInButtonComponent(
                            text = R.string.registroLand,
                            onClick = {
                                CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
                            }
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.moviecollage), // Reemplaza con tu imagen
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    )
                }
}})}

@Preview
@Composable
fun LandInScreenPreview(){
    LandInScreen()
}