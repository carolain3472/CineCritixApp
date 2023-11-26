package com.example.myapplication.app
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.MainScreen
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.screens.LandInScreen
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
            when(val screen = currentState.value) {

                is Screen.LandInScreen -> {
                    LandInScreen()
                }

                is Screen.RegistroScreen -> {
                    RegistroScreen()
                }

                is Screen.TerminosCondicionesScreen -> {
                    TerminosCondicionesScreen()
                }

                is Screen.Login -> {
                    LoginScreen()
                }

                is Screen.MainScreen -> {

                    MainScreen()
                }

                is Screen.MainScreenWithViewModel -> {
                    // Usa el ViewModel pasado como parámetro
                    MainScreen(userViewModel = screen.userViewModel)
                }


            }

        }
            
    }

}
