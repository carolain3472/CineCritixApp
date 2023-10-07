package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.myapplication.components.DividerTextComponent
import com.example.myapplication.components.NormalTextComponent
import com.example.myapplication.components.HeadingTextComponent
import com.example.myapplication.components.MyTextField
import com.example.myapplication.components.PasswordTextField
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen() {


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
            NormalTextComponent(value = stringResource(id = R.string.welcome))
            HeadingTextComponent(value = stringResource(id = R.string.acount))
            Spacer(modifier= Modifier.height(20.dp))
            MyTextField(labelValue= stringResource(id = R.string.myFirstName), painterResource(id = R.drawable.profile))
            Spacer(modifier = Modifier.height(10.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.password)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CheckboxComponent(value = stringResource(id = R.string.politica), onTextSelected = {
                CineCritixAppRouter.navigateTo(Screen.TerminosCondicionesScreen)
            })

            Spacer(modifier = Modifier.height(25.dp))
            ButtonComponent(value= stringResource(id = R.string.registro))
            DividerTextComponent()

            ClickableLoginTextComponent(value= "" , onTextSelected = {
                CineCritixAppRouter.navigateTo(Screen.Login)

            })

        }
    }

}





@Preview
@Composable

fun DefaultPreviewOfSignUpScreen(){
    RegistroScreen()

}