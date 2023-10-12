package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        topBar = { TopBar(modifier = Modifier.fillMaxWidth(), "Carolain Jimenez", navController) },
        bottomBar = { BottomBar(navController = navController) }
    ){contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)
        ) {
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
fun TopBar(modifier: Modifier = Modifier, nombre:String, navController: NavHostController){
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    val annotatedString = buildAnnotatedString {
        append("Hola, "+nombre+" !")
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = 0,
            end = length
        )
    }

    TopAppBar(
        title = { Text(
            text = annotatedString,
            fontSize = 18.sp
            //modifier = Modifier.padding(start = 100.dp)
        ) },
        backgroundColor = Color.White,
        actions = {


            IconButton(onClick = {
                showDialog = true
            }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Salir")
            }
            Box(modifier = Modifier.shadow(8.dp, shape = MaterialTheme.shapes.medium),){
                IconButton(
                    modifier = Modifier
                        .background(colorResource(id = R.color.buscarcard)),
                    onClick = { navController.navigate(BottomBarScreen.Search.route) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar")
                }
            }


        }
    )

    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clip(RoundedCornerShape(30.dp)) // Ajusta el radio según lo desees
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Cerrar sesión")
                },
                text = {
                    Text(text = "¿Está seguro de que desea cerrar sesión?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            // Aquí puedes agregar el código para cerrar sesión
                            val loginViewModel = RegistroViewModel()
                            loginViewModel.logout()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.sombraBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.sombraBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

