package com.example.myapplication.screens

import android.content.ContentResolver
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
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
import com.example.myapplication.components.ClickeableTextComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.ImagenURLCallBack
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.viewModel.LoginViewModel
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.example.myapplication.data.viewModel.UserViewModel
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
fun SettingsScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), userViewModel: UserViewModel = viewModel()){

    var boolEdit by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogFoto by remember { mutableStateOf(false) }
    var showDialogCambios by remember { mutableStateOf(false) }

    var nombre = userViewModel.userInfoResponse.value?.body()?.user_nombre
    var apellido = userViewModel.userInfoResponse.value?.body()?.user_apellido
    var correo = userViewModel.userInfoResponse.value?.body()?.user_email
    var urlImagen by remember { mutableStateOf(userViewModel.userInfoResponse.value?.body()?.user_profile.toString()) }


    userViewModel.getInfo(object : CallBackInfoUser {

        override fun onInfoResult(success: Response<UserInfoResponse>) {
            nombre = success.body()?.user_nombre
            apellido = success.body()?.user_apellido
            correo = success.body()?.user_email
            urlImagen = success.body()?.user_profile ?: ""
        }

    })


    var name by remember { mutableStateOf(nombre.toString()) }
    var lastname by remember { mutableStateOf(apellido.toString()) }
    var email by remember { mutableStateOf(correo.toString()) }

    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    var photo by remember { mutableStateOf("") }

    val context = LocalContext.current
    val file = File.createTempFile("imagegaleria", ".jpg")
    val contentResolver: ContentResolver = context.contentResolver


    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        //When the user has selected a photo, its URI is returned here
        val inputStream: InputStream? = uri?.let { contentResolver.openInputStream(it) }
        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }


        userViewModel.uploadImage(file= file, callback =
        object : ImagenURLCallBack {
            override fun onImageURLResult(success: String) {
                urlImagen=success

            }

        })

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

                Box{

                    IconButton(
                        modifier = Modifier
                            .background(Color.White),
                        onClick = {
                            showDialogFoto = true

                        }
                    ) {
                        val imagePainter =
                            painterResource(id = R.drawable.borrarimagen) // Reemplaza 'tu_imagen' con el nombre de tu imagen en drawables
                        Icon(
                            painter = imagePainter,
                            contentDescription = stringResource(id = R.string.eliminarFoto),

                            )
                    }
                }

                //Botones para editar la foto
                Row(modifier = Modifier.fillMaxWidth()) {
                    botonEditarImagen("Tomar Foto", { navController.navigate(BottomBarScreen.Camara.route ) })

                    botonEditarImagen("Elegir Foto", {
                        launcher.launch( PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )


                    } )

                    botonEditarImagen("Elegir Avatar",{ navController.navigate(BottomBarScreen.ElegirAvatar.route ) } )
                }


                Box{

                    IconButton(
                        modifier = Modifier
                            .background(Color.White),
                        onClick = {
                            navController.navigate(BottomBarScreen.UpdatePassword.route)
                        }
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
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

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
                            onButtonClicked = {
                                showDialogCambios = true
                                boolEdit=false},
                            isEnabled = boolEdit)

                        ClickeableTextComponent(
                            text="Eliminar Cuenta",
                            icon=Icons.Default.Delete,
                            onTextSelected = { showDialog=true },
                            tint= Color.Red

                        )



                    }
                }

            }



        }

    }



    var loginViewModel = LoginViewModel()



    if (showDialog || showDialogFoto || showDialogCambios) {
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
                    if(showDialog){
                        HeadingTextComponentBlack(
                            value = stringResource(id = R.string.eliminarCuenta))
                    }

                    if(showDialogFoto){
                        HeadingTextComponentBlack(
                            value = stringResource(id = R.string.eliminarFoto))
                    }

                    if(showDialogCambios){
                        HeadingTextComponentBlack(
                            value = "Guardar Cambios")
                    }

                },


                text = {

                        Row {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = colorResource(id = R.color.sombraBoton)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            if(showDialog) {
                                Text(text = "¿Estas seguro que deseas eliminar tu cuenta?")
                            }

                            if(showDialogFoto) {
                                Text(text = "¿Estas seguro que deseas eliminar tu foto de perfil?")
                            }
                            if(showDialogCambios){
                                Text(text = "¿Estas seguro que deseas guardar los cambios?")

                            }


                        }

                },

                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            //Llamar el endpoint para eliminar la cuenta y para cerrar sesión y volver al login

                            if(showDialog) {
                                Log.d(TAG3, "ELIMINAR CUENTA")
                                val loginViewModel = RegistroViewModel()
                                //userViewModel.logout()
                                userViewModel.eliminarCuenta()
                                loginViewModel.logout()
                            }

                            if(showDialogFoto) {
                                Log.d(TAG3, "ELIMINAR FOTO")
                                userViewModel.deleteImage(
                                    object : ImagenURLCallBack {
                                        override fun onImageURLResult(success: String) {
                                            urlImagen = success
                                        }

                                    }
                                )
                            }

                                if(showDialogCambios) {
                                    Log.d(TAG3, "GUARDAR CAMBIOS")
                                    userViewModel.updateDatos(name, lastname);
                                }

                                showDialogCambios=false
                                showDialogFoto=false


                        },
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.abajoBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.sombraBoton),
                            disabledContentColor = Color.White
                        ),

                    ) {
                        Text( stringResource(id = R.string.confirmar_salir), color=Color.White)
                    }
                },


                dismissButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false
                            showDialogFoto=false
                            showDialogCambios=false
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
fun botonEditarImagen(texto:String , onClick: () -> Unit){


    Button(onClick = onClick,
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
            .size(180.dp) // Tamaño del contenedor, puedes ajustarlo según tus necesidades
            .clip(CircleShape)
            .border(borderWidth, Color.White, CircleShape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
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

