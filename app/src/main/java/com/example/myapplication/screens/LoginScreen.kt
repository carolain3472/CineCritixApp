package com.example.myapplication.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.ClickablePasswordTextComponent
import com.example.myapplication.components.ClickableRegisterTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextField
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.data.viewModel.LoginAPIViewModel
import com.example.myapplication.data.LoginCallBack
import com.example.myapplication.data.viewModel.LoginViewModel
import com.example.myapplication.data.UIEventLogin
import com.example.myapplication.data.response.UserLoginResponse
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.android.gms.auth.api.signin.GoogleSignIn
import retrofit2.Response

private val TAG= "LOGIIN SCREEN"
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), loginAPIViewModel: LoginAPIViewModel = viewModel(), userViewModel: UserViewModel = viewModel()) {

    val viewModel = LoginViewModel()
    val user = rememberUpdatedState(viewModel.userLiveData.value).value
    var showErrorDialog by remember { mutableStateOf(false) }


    //loginViewModel.loginIUState.value.email= ""
    //loginViewModel.loginIUState.value.password=""

    Log.d(TAG, viewModel.allValidationPassed.value.toString())
    Log.d(TAG, loginViewModel.loginIUState.value.emailError.toString())
    Log.d(TAG, loginViewModel.loginIUState.value.passwordError.toString())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                Image(painterResource(id = R.drawable.logo), contentDescription = "Logo")
                Spacer(modifier = Modifier.height(15.dp))
                NormalTextComponent(value = stringResource(id = R.string.welcome))
                HeadingTextComponent(value = stringResource(id = R.string.login))
                Spacer(modifier = Modifier.height(20.dp))

                //SignInButton(viewModel)

                Spacer(modifier = Modifier.height(20.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        loginViewModel.onEvent(UIEventLogin.EmailChanged(it))
                        loginAPIViewModel.setEmail(it)
                    },
                    errorStatus = loginViewModel.loginIUState.value.emailError
                )

                Spacer(modifier = Modifier.height(30.dp))

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        loginViewModel.onEvent(UIEventLogin.PasswordChanged(it))
                        loginAPIViewModel.setContrasena(it)

                    },
                    errorStatus = loginViewModel.loginIUState.value.passwordError
                )

                Spacer(modifier = Modifier.height(30.dp))

                ClickablePasswordTextComponent(value = "", onTextSelected = { CineCritixAppRouter.navigateTo(Screen.ResetPasswordEmail) })

                Spacer(modifier = Modifier.height(10.dp))


                ButtonComponent(
                    value = stringResource(id = R.string.inicio), onButtonClicked = {
                        //loginViewModel.onEvent(UIEventLogin.LoginButtonClicked)
                        loginAPIViewModel.login(
                            object : LoginCallBack {

                                override fun onViewModelResult(success: Response<UserLoginResponse>) {
                                    // Implementa la lógica necesaria para manejar el resultado del ViewModel
                                    userViewModel.setUserLoginResponse(success)
                                }

                                override fun onLoginResult(success: Boolean) {
                                    if (success){
                                        CineCritixAppRouter.navigateToMainScreen(userViewModel)
                                        //CineCritixAppRouter.navigateTo(Screen.MainScreen)
                                    }else{
                                        showErrorDialog = true
                                    }
                                }



                            }


                        )
                    },
                    isEnabled = loginViewModel.allValidationPassed.value
                )



                DividerTextComponent()

                ClickableRegisterTextComponent(value =  R.string.registroLogin, onTextSelected = {
                    CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
                })

                SystemBackButtonHandler {
                    CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
                }
            }
        }

        if(loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }

        //Muestra una ventana emergente si hay algun error al realizar la solicitud
        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = {
                    showErrorDialog = false
                },
                title = { Text(text = "Error al iniciar sesión") },

                text = {
                       Text(text = "Las credenciales proporcionadas son inválidas. Intente nuevamente")
                },

                confirmButton = {
                    Button(
                        onClick = {
                            showErrorDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.sombraBoton),
                            contentColor = Color.White,
                            disabledContainerColor = colorResource(id = R.color.abajoBoton),
                            disabledContentColor = Color.White
                        ),
                    ) {
                        Text(text = "Aceptar")
                    }
                }
            )
        }
    }
}


@Composable
fun SignInButton(viewModel: LoginViewModel) {
    val user = rememberUpdatedState(viewModel.userLiveData.value).value
    val context = LocalContext.current
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.signInWithGoogle(context as Activity)
                val account = GoogleSignIn.getLastSignedInAccount(context)
                //updateUI(account)
                if (account != null) {
                    //val displayName = account.displayName // Obtener el nombre del usuario
                    val email = account.email // Obtener el correo electrónico del usuario
                    //val photoUrl = account.photoUrl // Obtener la URL de la foto de perfil del usuario
                    CineCritixAppRouter.navigateTo(Screen.MainScreen)
                    //navController.navigate(BottomBarScreen.Home.route)
                    Log.e(TAG, email.toString())
                }
            },
            enabled = user == null,
            modifier = Modifier
                .size(width = 100.dp, height = 25.dp)
                .fillMaxWidth()
                .heightIn(20.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)


        ) {

            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                alignment = Alignment.Center
            )


        }

    }
}



@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}