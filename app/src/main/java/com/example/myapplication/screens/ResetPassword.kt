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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingTextComponentBlack
import com.example.myapplication.components.MyTextField
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.NormalTextComponentBlack
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.data.CodeCallBack
import com.example.myapplication.data.UIEventLogin
import com.example.myapplication.data.viewModel.LoginViewModel
import com.example.myapplication.data.viewModel.ResetPasswordViewModel
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen

@Composable
fun ResetPassword(resetPasswordViewModel: ResetPasswordViewModel = viewModel()){
    var code by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    var loginViewModel = LoginViewModel()

    Scaffold(
        topBar = {
            IconButton(onClick = {
                CineCritixAppRouter.navigateTo(Screen.ResetPasswordEmail)
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



                HeadingTextComponentBlack(value = stringResource(id = R.string.nuevaContraseña))

                Spacer(modifier = Modifier.padding(10.dp))

                NormalTextComponentBlack(value = stringResource(id = R.string.label_email3))
                NormalTextComponentBlack(value = stringResource(id = R.string.label_email4))
                Spacer(modifier = Modifier.padding(10.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        resetPasswordViewModel.setNewPasswordEmail(it)
                    },
                    errorStatus = true
                )

                Spacer(modifier = Modifier.padding(7.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.codigo),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        resetPasswordViewModel.setNewPasswordToken(it)
                    },
                    errorStatus = true
                )

                Spacer(modifier = Modifier.padding(7.dp))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.nuevacontraseña),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        resetPasswordViewModel.setNewPassword(it)
                        loginViewModel.onEvent(UIEventLogin.PasswordChanged(it))

                    },
                    errorStatus = loginViewModel.loginIUState.value.passwordError

                )

                Spacer(modifier = Modifier.padding(10.dp))

                ButtonComponent(
                    value = "Actualizar Contraseña",
                    onButtonClicked = {
                        resetPasswordViewModel.NewPassword(
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
                        value = stringResource(id = R.string.respuestacodigo))
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
                        } else if(code==403) {
                            androidx.compose.material3.Text(
                                text = "El código ha expirado. Solicita otro código para poder recuperar tu contraseña"
                            )
                        }else if(code==409){
                            androidx.compose.material3.Text(
                                text = "Te queda solo un intento para ingresar el código correcto, de lo contrario se inhabilitará tu cuenta por 30 minutos"
                            )
                        }else if(code==406){
                            androidx.compose.material3.Text(
                                text = "Verifica que el código sea el correcto"
                            )
                        }else if(code==428){
                            androidx.compose.material3.Text(
                                text = "Ya no te quedan más intentos para ingresar el código. Vuelve a intentarlo más tarde"
                            )
                        }else{
                            androidx.compose.material3.Text(
                                text = "Error. Vuelve a solicitar el código"
                            )
                        }
                    }
                },
                confirmButton = {
                    androidx.compose.material.Button(
                        onClick = {
                            showDialog = false
                            // Aquí codigo para gestionar el codigo
                            if(code==200 ||code==403 ||code==428){
                                CineCritixAppRouter.navigateTo(Screen.Login)
                            }else{
                                CineCritixAppRouter.navigateTo(Screen.ResetPassword)
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
fun ResetPasswordPreview(){
    ResetPassword()
}