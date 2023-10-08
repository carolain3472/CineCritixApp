package com.example.myapplication.app
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.RegistroScreen
import com.example.myapplication.screens.TerminosCondicionesScreen

@Composable
fun CineCritixApp(){
    Surface(
        modifier= Modifier.fillMaxSize(),
        color= Color.Black ) {

        Crossfade(targetState = CineCritixAppRouter.currentScreen) {
            currentState ->
            when(currentState.value){

                is Screen.RegistroScreen -> {
                    RegistroScreen()
                }
                is Screen.TerminosCondicionesScreen -> {
                    TerminosCondicionesScreen()
                }
                is Screen.Login -> {
                    LoginScreen()
                }

                is Screen.HomeScreen ->{
                    HomeScreen()
                }
            }
            
        }

    }
}
