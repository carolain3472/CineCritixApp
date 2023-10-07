package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator

class LoginViewModel: ViewModel() {
    private val TAG= LoginViewModel::class.simpleName

    var registrationIUState = mutableStateOf(RegistrationIUState())

    fun onEvent(event:UIEvent){
        validateDataWithRules()
        when(event){
            is UIEvent.FirstNameChanged -> {
                registrationIUState.value= registrationIUState.value.copy(
                    firstName= event.firsName
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                registrationIUState.value = registrationIUState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                registrationIUState.value = registrationIUState.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                registro()
            }

        }

    }

    private fun registro() {
        Log.d(TAG, "Inside_ signUp")
        printState()

        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val fNameResult= Validator.validateName(
            fname= registrationIUState.value.firstName )

        val emailResult= Validator.validateEmail(
            email = registrationIUState.value.email )

        val passwordResult= Validator.validatePassword(
            password = registrationIUState.value.password )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")

        registrationIUState.value = registrationIUState.value.copy(
            nameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
    }


    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationIUState.value.toString() )

    }
}