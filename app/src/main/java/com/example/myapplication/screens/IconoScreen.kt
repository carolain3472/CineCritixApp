package com.example.myapplication.screens


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun IconoScreen(navController: NavHostController = rememberNavController(), userViewModel: UserViewModel = viewModel()){
    var showDialog by remember { mutableStateOf(false) }


    val imageList = listOf(
        R.drawable.icono1,
        R.drawable.icono2,
        R.drawable.icono3,
        R.drawable.icono4,
        R.drawable.icono5,
        R.drawable.icono6,
        R.drawable.icono7,
        R.drawable.icono8,
        R.drawable.icono9,
        R.drawable.icono10,
        R.drawable.icono11,
        R.drawable.icono12,
    )

    val imageName = listOf(
        "icono1.png",
        "icono2.png",
        "icono3.png",
        "icono4.png",
        "icono5.png",
        "icono5.png",
        "icono6.png",
        "icono7.png",
        "icono8.png",
        "icono9.png",
        "icono10.png",
        "icono11.png",
        "icono12.png",

    )

    var selectedImageIndex by remember { mutableIntStateOf(R.drawable.icono2) }
    var enableBool by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("")  }
    name = LocalContext.current.resources.getResourceEntryName(selectedImageIndex)+".png"


    Scaffold(
        topBar = {
            IconButton(
                enabled = true,
                onClick = {
                navController.navigate(BottomBarScreen.Settings.route)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.salir)
                )
            }
        },

        bottomBar ={// Botón para confirmar la selección
                        ButtonComponent(value = "Seleccionar",
                            onButtonClicked = { showDialog=true},
                            isEnabled = enableBool)
                   },


        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)


    ) { contentPadding ->




        Spacer(modifier = Modifier.padding(30.dp))


        HeadingTextComponentBlack(value = stringResource(id = R.string.iconoFoto))

        Spacer(modifier = Modifier.padding(10.dp))


        LazyColumn( modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
        {
            items(imageList.chunked(2)){
                rowImages ->

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            rowImages.forEach { imageIndex ->
                Log.d(TAG3, imageIndex.toString())

                ImageItem(imageRes = imageIndex ,
                    isSelected = selectedImageIndex == imageIndex,
                    userViewModel= userViewModel,
                    onImageClick = {
                        selectedImageIndex = imageIndex;
                        enableBool=true;
                        Log.d(TAG3, "NOMBRE ARCHIVO")
                        Log.d(TAG3, name)


                    })



                }
            }
        }
    }

    }


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
                    HeadingTextComponentBlack(
                        value = "¿Estas seguro del avatar que seleccionaste?")
                },


                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false
                            // Aquí codigo para gestionar el codigo
                            userViewModel.enviarIcono(name)
                            navController.navigate(BottomBarScreen.Settings.route)

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.sombraBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text( stringResource(id = R.string.confirmar_salir))
                    }
                },

                dismissButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false
                        },
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.sombraBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text(stringResource(id = R.string.cancelar_salir))
                    }
                }





            )
        }
    }

}



@Composable
fun ImageItem(
    imageRes: Int,
    isSelected: Boolean,
    onImageClick: (Int) -> Unit,
    userViewModel: UserViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { onImageClick(imageRes)  }
            .background(if (isSelected) colorResource(id = R.color.sombraBoton) else Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}




@Preview
@Composable
fun IconoScreenPreview(){
    MyApplicationTheme {
        IconoScreen()
    }

}