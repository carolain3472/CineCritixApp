package com.example.myapplication.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import com.example.myapplication.components.NormalTextComponentBlack
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.data.CodeCallBack
import com.example.myapplication.data.UIEventLogin
import com.example.myapplication.data.viewModel.LoginViewModel
import com.example.myapplication.data.viewModel.UserViewModel


@Composable
fun UpdatePasswordScreen(navController: NavHostController = rememberNavController(), userViewModel: UserViewModel = viewModel()){
    var code by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    var loginViewModel = LoginViewModel()

    Scaffold(
        topBar = {
            IconButton(onClick = {
                navController.navigate(BottomBarScreen.Settings.route)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.salir)
                )
            }
        },

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)) {contentPadding ->


        LazyColumn(modifier = Modifier.padding(contentPadding))
        {

            items(1){



                HeadingTextComponentBlack(value = stringResource(id = R.string.cambiarpass))

                Spacer(modifier = Modifier.padding(10.dp))

                NormalTextComponentBlack(value = stringResource(id = R.string.label_email5))
                Spacer(modifier = Modifier.padding(10.dp))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.nuevacontraseña),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        loginViewModel.onEvent(UIEventLogin.PasswordChanged(it))
                        userViewModel.setNewPassword(it)
                    },
                    errorStatus = loginViewModel.loginIUState.value.passwordError

                )

                Spacer(modifier = Modifier.padding(10.dp))

                ButtonComponent(
                    value = "Actualizar Contraseña",
                    onButtonClicked = {

                        userViewModel.updateContrasena(
                            object : CodeCallBack {
                            override fun onCodeResult(success: Int) {
                                code = success
                                showDialog = true
                            }

                        }
                        )



                    }, isEnabled = loginViewModel.loginIUState.value.passwordError )


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
                        value = stringResource(id = R.string.respuestacodigo1))
                },
                text = {
                    val color = when(code){
                        200-> Color.Green
                        else->Color.Red
                    }

                    val icon = when (code) {
                        200 -> Icons.Default.CheckCircle
                        else -> Icons.Default.Warning
                    }
                    Row {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = color
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        if (code == 200) {
                            androidx.compose.material3.Text(text = "Contraseña actualizada con éxito.")
                        }else{
                            androidx.compose.material3.Text(
                                text = "Error al intentar cambiar la contraseña"
                            )
                        }
                    }
                },
                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false
                            // Aquí codigo para gestionar el codigo
                            if(code==200){
                                navController.navigate(BottomBarScreen.Settings.route)
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.sombraBoton),
                            contentColor = colorResource(id = R.color.arribaBoton),
                            disabledBackgroundColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        )
                    ) {
                        androidx.compose.material3.Text( stringResource(id = R.string.confirmar_salir))
                    }
                }

            )
        }
    }

}

@Preview
@Composable
fun UpdatePasswordScreenPreview(){
    UpdatePasswordScreen()
}