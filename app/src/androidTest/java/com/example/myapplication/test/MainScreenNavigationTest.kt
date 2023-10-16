package com.example.myapplication.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.MainScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.myapplication.R

class MainScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MainScreen(navController = navController)

        }
    }

    //Confirma que el nombre de ruta esperado (en este caso, BottomBarScreen.Home.route) sea igual a
    // la ruta de destino de la entrada actual de la pila de actividades del controlador de navegación.
    @Test
    fun mainNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(BottomBarScreen.Home.route )
    }


    //Verifica la navegacion a la pantalla de favoritos
    @Test
    fun mainNavHost_clickOne_navigatesToSelectFavoriteScreen(){
        composeTestRule.onNodeWithContentDescription( R.string.favorite).performClick()
        navController.assertCurrentRouteName(BottomBarScreen.Favorite.route)
    }

    //Verifica la navegacion a la pantalla de review
    @Test
    fun mainNavHost_clickOne_navigatesToSelectReviewScreen(){
        composeTestRule.onNodeWithContentDescription( R.string.reviews).performClick()
        navController.assertCurrentRouteName(BottomBarScreen.Review.route)
    }

    //Verifica la navegacion a la pantalla de settings
    @Test
    fun mainNavHost_clickOne_navigatesToSelectSettingsScreen(){
        composeTestRule.onNodeWithContentDescription( R.string.settings).performClick()
        navController.assertCurrentRouteName(BottomBarScreen.Settings.route)
    }

    //Verifica la navegacion a la pantalla de home desde la pantalla de settings
    @Test
    fun mainNavHost_clickOne_navigatesToSelectHomeScreen(){
        composeTestRule.onNodeWithContentDescription( R.string.settings).performClick()
        composeTestRule.onNodeWithContentDescription( R.string.home).performClick()
        navController.assertCurrentRouteName(BottomBarScreen.Home.route)
    }


    //Verifica la navegacion a la pantalla de search
    @Test
    fun mainNavHost_clickOne_navigatesToSelectSearchScreen(){

        composeTestRule.onNodeWithContentDescription( R.string.search).performClick()
        navController.assertCurrentRouteName(BottomBarScreen.Search.route)
    }


    //Verifica que la ventana de alerta al dar salir salga
    //verifica que se pueda dar click al boton de confirmar cerrar sesion

    @Test
    fun mainNavHost_clickOne_navigatesToExitButton() {

        // Encuentra y haz clic en el botón de salida
        composeTestRule.onNodeWithContentDescription(R.string.salir).performClick()

        // Verifica si el diálogo de confirmación se muestra correctamente
        composeTestRule
            .onNodeWithStringId(R.string.cerrarSesion)
            .assertIsDisplayed()

        // Encuentra y haz clic en el botón de confirmación en el diálogo
        composeTestRule
            .onNodeWithStringId(R.string.confirmar_salir)
            .performClick()

        //CineCritixAppNavigation.navigateToLoginScreen()

        // Verifica si la acción de cerrar sesión se realiza correctamente
        // Puedes agregar aquí alguna lógica para verificar que se haya cerrado la sesión
    }



}