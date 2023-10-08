package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG= LoginViewModel::class.simpleName

    var loginIUState = mutableStateOf(LoginIUState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event:UIEventLogin){
        when(event){
            is UIEventLogin.EmailChanged -> {
                loginIUState.value= loginIUState.value.copy(
                    email= event.email
                )
            }

            is UIEventLogin.PasswordChanged -> {
                loginIUState.value= loginIUState.value.copy(
                    password= event.password
                )

            }

            is UIEventLogin.LoginButtonClicked -> {
                login()


            }
        }
        validateLoginIUDataWithRules()

    }


    private fun validateLoginIUDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = loginIUState.value.email
        )

        val passwordResult= Validator.validatePassword(
            password= loginIUState.value.password
        )

        loginIUState.value= loginIUState.value.copy(
            emailError = emailResult.status,
            passwordError=  passwordResult.status
        )

        allValidationPassed.value = emailResult.status && passwordResult.status


    }

    private fun login() {

        loginInProgress.value = true
        val email = loginIUState.value.email
        val password= loginIUState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{

                Log.d(TAG, "Inside_Login_Success")
                Log.d(TAG, "${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value=false
                    CineCritixAppRouter.navigateTo(Screen.HomeScreen)
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login:failure")
                Log.d(TAG, "${it.localizedMessage}")



            }

    }

}