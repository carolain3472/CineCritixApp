package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val TAG= LoginViewModel::class.simpleName

    var registrationIUState = mutableStateOf(RegistrationIUState())

    fun onEvent(event:UIEvent){
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
    }

    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationIUState.value.toString() )

    }
}