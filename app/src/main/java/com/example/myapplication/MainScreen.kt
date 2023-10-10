package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.RegistroViewModel
import com.example.myapplication.navigation.BottomNavGraph



@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        topBar = { TopBar(modifier = Modifier.fillMaxWidth()) },
        bottomBar = { BottomBar(navController = navController) }
    ){contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            BottomNavGraph(
                navController = navController)

        }

    }
}


@Composable
    fun BottomBar(navController: NavHostController) {
    /**val colors = NavigationBarItemColors(
        selectedIconColor = colorResource(id = R.color.sombraBoton),
        disabledIconColor = Color.LightGray ,
        disabledTextColor = Color.LightGray,
        selectedIndicatorColor = colorResource(id = R.color.sombraBoton),
        selectedTextColor = Color.White,
        unselectedIconColor = Color.LightGray,
        unselectedTextColor = Color.LightGray
    )*/
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favorite,
        BottomBarScreen.Review,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: BottomBarScreen.Home

    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Black,
        contentColor = colorResource(id = R.color.sombraBoton)
    ) {
        // Aquí colocas tus elementos del menú
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = { navController.navigate(screen.route) },
                //colors = colors,
                icon = {
                    val iconTint = if (currentDestination == screen.route) {
                        Color.Black // Color cuando está seleccionado
                    } else {
                        Color.White // Color cuando no está seleccionado
                    }
                    Icon(
                        imageVector = screen.icon,
                        tint = iconTint,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )


        }
    }
    }

    @Composable
    fun TopBar(modifier: Modifier = Modifier){

        TopAppBar(
            title = { Text(
                text = "Carolain Jimenez",
                modifier = Modifier.padding(start = 100.dp)
            ) },
            backgroundColor = Color.White,
            actions = {
                IconButton(onClick = {
                    val loginViewModel = RegistroViewModel()
                    loginViewModel.logout()
                }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Salir")
                }


            }
            )
    }
