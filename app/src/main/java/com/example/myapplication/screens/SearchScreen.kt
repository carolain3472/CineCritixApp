package com.example.myapplication.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R


@Composable
fun SearchScreen( navController: NavHostController = rememberNavController()){
    Box(modifier = Modifier.fillMaxWidth()
        .background(Color.White),
        contentAlignment = Alignment.Center
        ) {
        Surface(modifier = Modifier
            .shadow(8.dp, shape = MaterialTheme.shapes.medium)
            .background(Color.Black)
            .width(360.dp),
            contentColor = Color.Black
        ){

            Column (modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(),
                horizontalAlignment =  androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

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

                ButtonCategories("Acción", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Aventura", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Romance", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Comedia", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Ciencia Ficción", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Fantasía", navController, onClick = {})
                Spacer(modifier = Modifier.size(10.dp))
                ButtonCategories("Drama", navController, onClick = {})

                Spacer(modifier = Modifier.size(30.dp))

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
        onClick = { onClick }) {
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
