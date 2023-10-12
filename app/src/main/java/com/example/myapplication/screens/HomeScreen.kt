package com.example.myapplication.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.CardSlider
import com.example.myapplication.components.ClickeableTextComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.components.movieCard
import com.example.myapplication.data.RegistroViewModel
import com.example.myapplication.navigation.BottomNavGraph

@Composable
fun HomeScreen( navController: NavHostController, loginViewModel: RegistroViewModel = viewModel()){

    LazyColumn(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ){
            items(1){

                ClickeableTextComponent(text = "Mejores Puntuaciones",
                    icon = Icons.Default.KeyboardArrowRight,
                    onTextSelected = {}
                    )
                CardSlider()

                ClickeableTextComponent(text = "Descubre nuevos lanzamientos",
                    icon = Icons.Default.KeyboardArrowRight,
                    onTextSelected = {}
                )

                Column (modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ){
                    Row (modifier = Modifier.fillMaxWidth()
                        , verticalAlignment = Alignment.CenterVertically
                        , horizontalArrangement = Arrangement.Center){
                        movieCard( R.drawable.barbie,  {},"Barbie",
                            "Narra la historia de Barbie en el mundo real, en donde debe reconocer sus capacidades y realizar lo que dicte su corazon para salvar Barbieland. ")
                        Spacer(modifier = Modifier.size(5.dp))
                        movieCard( R.drawable.grinch, {},"El grinch",
                            "Narra la historia del cascarrabias Grinch, quien intenta robar la Navidad de los habitantes de Villaquien, pero descubre el verdadero significado de la festividad en el proceso.")

                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Row (modifier = Modifier.fillMaxWidth()
                        , verticalAlignment = Alignment.CenterVertically
                        , horizontalArrangement = Arrangement.Center){
                        movieCard( R.drawable.coco, { }, "Coco",
                            "Sigue la historia de Miguel, apasionado por la música que se embarca en un viaje emocional a la Tierra de los Muertos en busca de su ancestro y descubre la importancia de la familia y las tradiciones mexicanas.")
                        Spacer(modifier = Modifier.size(5.dp))
                        movieCard( R.drawable.matrix,{}, "Matrix",
                            "Presenta la historia de Neo, un hacker que descubre que el mundo en el que vive es una simulación controlada por máquinas. Se une a una resistencia para luchar contra las máquinas y desvelar la verdad detrás de la realidad.")

                    }

                    Spacer(modifier = Modifier.size(20.dp))
                }

            }


        }
}

//navController.navigate(BottomBarScreen.Favorite.route)


