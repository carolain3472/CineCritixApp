package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen

@Composable
fun TerminosCondicionesScreen(){
    Scaffold(
        topBar = {IconButton(onClick = {
            CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.salir))
        }},

        modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {contentPadding ->


        LazyColumn(modifier = Modifier.padding(contentPadding))
        {

            items(1){



                HeadingTextComponentBlack(value = stringResource(id = R.string.terminosCondiciones))

                Text(text = stringResource(id = R.string.texto_condiciones))

                Spacer(modifier = Modifier.padding(10.dp))

                HeadingTextComponentBlack(value = stringResource(id = R.string.politicaPrivacidad))

                Text(text = stringResource(id = R.string.politicatext))

                Spacer(modifier = Modifier.padding(10.dp))


                HeadingTextComponentBlack(value = stringResource(id = R.string.autorizacion))

                Text(text = stringResource(id = R.string.datospersonalestext))





            }

        }









    }
}

@Preview
@Composable
fun TerminosCondicionesScreenPreview(){
    TerminosCondicionesScreen()
}