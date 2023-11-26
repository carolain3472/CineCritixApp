package com.example.myapplication.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.viewModel.UserViewModel


sealed class Screen(){
    object LandInScreen: Screen()
    object RegistroScreen: Screen()
    object TerminosCondicionesScreen: Screen()
    object Login: Screen()

    object MainScreen:Screen()

    object ResetPasswordEmail:Screen()

    object ResetPassword:Screen()

    data class MainScreenWithViewModel(val userViewModel: UserViewModel) : Screen()


}

object CineCritixAppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.LandInScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value= destination
    }

    // Nueva función para navegar a MainScreen con ViewModel
    fun navigateToMainScreen(userViewModel: UserViewModel) {
        navigateTo(Screen.MainScreenWithViewModel(userViewModel))
    }
}




