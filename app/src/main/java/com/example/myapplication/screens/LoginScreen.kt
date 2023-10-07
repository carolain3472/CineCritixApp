package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.CheckboxComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.ClickablePasswordTextComponent
import com.example.myapplication.components.ClickableRegisterTextComponent
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextField
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.data.UIEvent
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen() {
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
        Spacer(modifier = Modifier.height(25.dp))
        NormalTextComponent(value = stringResource(id = R.string.welcome))
        HeadingTextComponent(value = stringResource(id = R.string.login))
        Spacer(modifier = Modifier.height(30.dp))
        MyTextField(
            labelValue = stringResource(id = R.string.email),
            painterResource(id = R.drawable.email),
            onTextSelected = {

            }
        )
        
        Spacer(modifier = Modifier.height(30.dp))

        PasswordTextField(
            labelValue = stringResource(id = R.string.password),
            painterResource(id = R.drawable.password),
            onTextSelected = {

            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        ClickablePasswordTextComponent(value= "" , onTextSelected = {

        })
        Spacer(modifier = Modifier.height(30.dp))

        ButtonComponent(value= stringResource(id = R.string.inicio),onButtonClicked = {

        })
        DividerTextComponent()

        ClickableRegisterTextComponent(value= "" , onTextSelected = {
            CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
        })

        SystemBackButtonHandler{
            CineCritixAppRouter.navigateTo(Screen.RegistroScreen)
        }
    }
}
}


@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}