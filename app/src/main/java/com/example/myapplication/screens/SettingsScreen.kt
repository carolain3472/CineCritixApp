package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.example.myapplication.data.viewModel.UserViewModel

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), userViewModel: UserViewModel = viewModel()){

    userViewModel.getInfo()

    var boolEdit by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var nombre = userViewModel.userInfoResponse.value?.body()?.user_nombre
    var apellido = userViewModel.userInfoResponse.value?.body()?.user_apellido
    var correo = userViewModel.userInfoResponse.value?.body()?.user_email
    var imagen = userViewModel.userInfoResponse.value?.body()?.user_profile
    var urlImagen = "https://cinecritixbackend.onrender.com/media/"+imagen


    var name by remember { mutableStateOf("Nombre") }
    var lastname by remember { mutableStateOf("Apellido") }
    var email by remember { mutableStateOf("Correo") }

    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }





    if (nombre != null && correo != null  && apellido!=null) {
        name = nombre
        email = correo
        lastname=apellido
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            items(1){
                ImageWithCircularBorder(urlImagen, 5.dp)


                //Botones para editar la foto
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                    botonEditarImagen("Tomar Foto", navController,BottomBarScreen.Camara.route )
                    botonEditarImagen("Elegir Foto", navController,BottomBarScreen.Camara.route )
                    botonEditarImagen("Elegir Avatar", navController,BottomBarScreen.Camara.route )
                }

                Box{

                    IconButton(
                        modifier = Modifier
                            .background(Color.White),
                        onClick = { showDialog = true}
                    ) {
                        val imagePainter =
                            painterResource(id = R.drawable.updatecontrasena) // Reemplaza 'tu_imagen' con el nombre de tu imagen en drawables
                        Icon(
                            painter = imagePainter,
                            contentDescription = stringResource(id = R.string.updatecontra),

                        )
                    }
                }



                //CameraView(isButtonClicked)

                Card(
                    modifier = modifier
                        .background(colorResource(R.color.grisClaro))
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(8.dp)
                        .clip(RectangleShape)


                ) {
                    Column(
                        modifier = Modifier
                            .background(colorResource(R.color.grisClaro))
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Nombre:",
                            color= colorResource(id = R.color.abajoBoton),
                            modifier= Modifier.padding(bottom=8.dp))

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it; boolEdit= true },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null,  tint = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp)
                                .height(48.dp)
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.colorPrimary), // Establece el color del borde a amarillo
                                    //shape = MaterialTheme.shapes.medium
                                )
                                .background(Color.Black),

                            textStyle = TextStyle.Default.copy(color = Color.White)

                        )

                        Text(
                            text = "Apellido:",
                            color= colorResource(id = R.color.abajoBoton),
                            modifier= Modifier.padding(bottom=8.dp))

                        OutlinedTextField(
                            value = lastname,
                            onValueChange = { lastname = it; boolEdit=true },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null,  tint = Color.White) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp)
                                .height(48.dp)
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.colorPrimary), // Establece el color del borde a amarillo
                                    //shape = MaterialTheme.shapes.medium
                                )
                                .background(Color.Black),

                            textStyle = TextStyle.Default.copy(color = Color.White)

                        )

                        Text(
                            text = "Correo Electronico:",
                            color= colorResource(id = R.color.abajoBoton),
                            modifier= Modifier.padding(bottom=8.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.DarkGray) },
                            enabled = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp)
                                .height(48.dp)
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.colorPrimary), // Establece el color del borde a amarillo

                                )
                                .background(Color.Black),

                            textStyle = TextStyle.Default.copy(color = Color.DarkGray )
                        )

                        ButtonComponent(value = "Guardar Cambios",
                            onButtonClicked = { userViewModel.updateDatos(name, lastname)},
                            isEnabled = boolEdit)



                    }
                }

            }



        }

    }



    //ShowDialog

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
                    Text(text = stringResource(id = R.string.updatepass),
                    modifier=Modifier.padding(bottom = 10.dp))
                },



                text = {
                    Column {
                        Spacer(modifier = modifier.padding(10.dp))

                        //campo contraseña
                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it},
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Lock, contentDescription = "Contraseña", tint = Color.White)
                            },
                            trailingIcon = {
                                val iconPainter = if (passwordVisible) {
                                    androidx.compose.ui.res.painterResource(id = R.drawable.visibilityon)
                                } else {
                                    androidx.compose.ui.res.painterResource(id = R.drawable.visibilityoff)
                                }

                                var description = if (passwordVisible) {
                                    stringResource(id = R.string.hide)
                                } else {
                                    stringResource(id = R.string.show)
                                }

                                IconButton(
                                    onClick = { passwordVisible = !passwordVisible }
                                ) {
                                    Icon(painter = iconPainter, contentDescription = description, modifier=Modifier.size(25.dp))
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp)
                                .height(48.dp)
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.colorPrimary), // Establece el color del borde a amarillo

                                )
                                .background(Color.Black),

                            textStyle = TextStyle.Default.copy(color = Color.White))


                    }
                },

                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false

                            userViewModel.updateContrasena(newPassword)
                            newPassword=""
                        },
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
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
                            newPassword=""
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
fun botonEditarImagen(texto:String ,navController: NavHostController = rememberNavController(), ruta: String){
    Button(onClick = { navController.navigate(ruta)},
        // Ajusta el tamaño del botón según tus necesidades
        colors= ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(modifier = Modifier

            .heightIn(30.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(

                        colorResource(id = R.color.abajoBoton),
                        colorResource(id = R.color.sombraBoton)
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            ),

            contentAlignment = Alignment.Center

        )
        {
            Text(text = texto, fontSize = 10.sp, modifier = Modifier.padding(5.dp))
        }

    }
}

@Composable
fun ImageWithCircularBorder(
    image: String,
    borderWidth: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .padding(borderWidth)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.error)
        )
    }
}




@Preview
@Composable
fun previewSettings(){
    SettingsScreen()
}

