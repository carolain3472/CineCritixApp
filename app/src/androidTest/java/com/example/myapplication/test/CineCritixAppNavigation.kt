package com.example.myapplication.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.myapplication.R
import com.example.myapplication.app.CineCritixApp
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.CineCritixAppRouter.navigateTo
import com.example.myapplication.navigation.Screen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.log

class CineCritixAppNavigation {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUpCineCritixAppNavigation() {
        composeTestRule.setContent {
            CineCritixApp()
        }
    }

    /**
    @Test
    fun testLandInScreen() {
        navigateTo(Screen.LandInScreen)
        // Agrega aquí la lógica de verificación para LandInScreen
        // Por ejemplo, verifica si los elementos de LandInScreen están presentes
        val currentScreen = CineCritixAppRouter.currentScreen.value
        assertEquals(Screen.LandInScreen, currentScreen)

    }*/

    /**
    @Test
    fun testRegistroScreen() {
        navigateTo(Screen.RegistroScreen)
        // Agrega aquí la lógica de verificación para RegistroScreen
        // Por ejemplo, verifica si los elementos de RegistroScreen están presentes
        val currentScreen = CineCritixAppRouter.currentScreen.value
        assertEquals(Screen.RegistroScreen, currentScreen)
    }*/


    @Test
    fun testLandScreentoLoginScreen() {
        //navigateTo(Screen.Login)
        composeTestRule.onNodeWithStringId(R.string.inciarLand).performClick()
        // Agrega aquí la lógica de verificación para RegistroScreen
        // Por ejemplo, verifica si los elementos de RegistroScreen están presentes
        val currentScreen = CineCritixAppRouter.currentScreen.value
        assertEquals(Screen.Login, currentScreen)
    }


    @Test
    fun testLandScreentoRegisterScreen() {

        composeTestRule.onNodeWithStringId(R.string.registroLand).performClick()
        // Agrega aquí la lógica de verificación para RegistroScreen
        // Por ejemplo, verifica si los elementos de RegistroScreen están presentes
        val currentScreen = CineCritixAppRouter.currentScreen.value
        assertEquals(Screen.LandInScreen, currentScreen)
    }

    /**@Test
    fun testLoginScreentoRegisterScreen() {
        //navigateTo(Screen.Login)
        composeTestRule.onNodeWithStringId(R.string.inciarLand).performClick()
        composeTestRule.onNodeWithText("Registrate aquí.", useUnmergedTree = true).performClick()
        // Agrega aquí la lógica de verificación para RegistroScreen
        // Por ejemplo, verifica si los elementos de RegistroScreen están presentes
        val currentScreen = CineCritixAppRouter.currentScreen.value
        assertEquals(Screen.RegistroScreen, currentScreen)
    }*/






}