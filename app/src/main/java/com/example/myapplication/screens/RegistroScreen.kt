package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.CheckboxComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextField
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.data.viewModel.RegisterAPIViewModel
import com.example.myapplication.data.RegisterCallback
import com.example.myapplication.data.viewModel.RegistroViewModel
import com.example.myapplication.data.UIEventRegistro
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(loginViewModel: RegistroViewModel = viewModel(), registerViewModel: RegisterAPIViewModel = viewModel()) {

    var showErrorDialog by remember { mutableStateOf(false) }


    Box(modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(28.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {

                items(1) {

                    Image(painterResource(id = R.drawable.logo), contentDescription = "Logo")
                    NormalTextComponent(value = stringResource(id = R.string.welcome))
                    HeadingTextComponent(value = stringResource(id = R.string.acount))
                    Spacer(modifier = Modifier.height(20.dp))


                    MyTextField(
                        labelValue = stringResource(id = R.string.documento),
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            registerViewModel.setDocumento(it)
                            loginViewModel.onEvent(UIEventRegistro.DocumentChanged(it))
                        },
                        errorStatus = loginViewModel.registrationIUState.value.docError

                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    MyTextField(
                        labelValue = stringResource(id = R.string.myFirstName),
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            registerViewModel.setNombre(it)
                            loginViewModel.onEvent(UIEventRegistro.FirstNameChanged(it))

                        },
                        errorStatus = loginViewModel.registrationIUState.value.nameError
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    MyTextField(
                        labelValue = stringResource(id = R.string.apellido),
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            registerViewModel.setApellido(it)
                            loginViewModel.onEvent(UIEventRegistro.FirstNameChanged(it))

                        },
                        errorStatus = loginViewModel.registrationIUState.value.nameError
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    MyTextField(
                        labelValue = stringResource(id = R.string.email),
                        painterResource(id = R.drawable.email),
                        onTextSelected = {
                            registerViewModel.setEmail(it)
                            loginViewModel.onEvent(UIEventRegistro.EmailChanged(it))

                        },
                        errorStatus = loginViewModel.registrationIUState.value.emailError

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PasswordTextField(
                        labelValue = stringResource(id = R.string.password),
                        painterResource(id = R.drawable.password),
                        onTextSelected = {
                            registerViewModel.setContrasena(it)
                            loginViewModel.onEvent(UIEventRegistro.PasswordChanged(it))

                        },
                        errorStatus = loginViewModel.registrationIUState.value.passwordError
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    CheckboxComponent(value = stringResource(id = R.string.politica),
                        onTextSelected = {
                            CineCritixAppRouter.navigateTo(Screen.TerminosCondicionesScreen)
                        },

                        onCheckedChange = {
                            loginViewModel.onEvent(UIEventRegistro.PrivacyPolicyCheckBoxClicked(it))

                        })

                    Spacer(modifier = Modifier.height(25.dp))

                    ButtonComponent(
                        value = stringResource(id = R.string.registro), onButtonClicked = {
                            loginViewModel.onEvent(UIEventRegistro.RegisterButtonClicked)
                            registerViewModel.register(
                                object : RegisterCallback {

                                    override fun onRegisterResult(success: Boolean) {
                                        if (success) {
                                            CineCritixAppRouter.navigateTo(Screen.Login)
                                        } else {
                                            showErrorDialog = true
                                        }
                                    }
                                }
                            )
                        },
                        isEnabled = true
                    )
                    DividerTextComponent()

                    ClickableLoginTextComponent(value = "", onTextSelected = {
                        CineCritixAppRouter.navigateTo(Screen.Login)

                    })


                }
            }
        }



        if(loginViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }

        //Muestra una ventana emergente si hay algun error al realizar la solicitud
        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = {
                    showErrorDialog = false
                },
                title = { Text(text = "Error") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showErrorDialog = false
                        }
                    ) {
                        Text(text = "Aceptar")
                    }
                }
            )
        }


    }

}






@Preview
@Composable

fun DefaultPreviewOfSignUpScreen(){
    RegistroScreen()

}