package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.myapplication.data.UIEventLogin
import com.example.myapplication.data.viewModel.ResetPasswordViewModel
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen

@Composable
fun ResetPasswordEmail(resetPasswordViewModel: ResetPasswordViewModel = viewModel()){
    Scaffold(
        topBar = {
            IconButton(onClick = {
            CineCritixAppRouter.navigateTo(Screen.Login)
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



                HeadingTextComponentBlack(value = stringResource(id = R.string.recuperarContraseña))

                Spacer(modifier = Modifier.padding(10.dp))

                NormalTextComponentBlack(value = stringResource(id = R.string.label_email))
                Spacer(modifier = Modifier.padding(5.dp))
                NormalTextComponentBlack(value = stringResource(id = R.string.label_email2))


                Spacer(modifier = Modifier.padding(10.dp))
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        resetPasswordViewModel.setEmail(it)
                    },
                    errorStatus = true
                )

                Spacer(modifier = Modifier.padding(10.dp))

                ButtonComponent(
                    value = "Enviar Codigo",
                    onButtonClicked = {
                        resetPasswordViewModel.ResetPassword()
                        CineCritixAppRouter.navigateTo(Screen.ResetPassword)},
                    isEnabled = true)


            }

        }









    }
}

@Preview
@Composable
fun ResetPasswordEmailPreview(){
    ResetPasswordEmail()
}