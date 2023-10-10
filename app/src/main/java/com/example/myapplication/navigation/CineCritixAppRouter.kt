package com.example.myapplication.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(){
    object RegistroScreen: Screen()
    object TerminosCondicionesScreen: Screen()
    object Login: Screen()

    object MainScreen:Screen()

}

object CineCritixAppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.RegistroScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value= destination
    }
}




