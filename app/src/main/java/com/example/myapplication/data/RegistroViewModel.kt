package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class RegistroViewModel: ViewModel() {
    private val TAG= RegistroViewModel::class.simpleName

    var registrationIUState = mutableStateOf(RegistrationIUState())

    var allValidationPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event:UIEventRegistro){
        validateDataWithRules()
        when(event){
            is UIEventRegistro.FirstNameChanged -> {
                registrationIUState.value= registrationIUState.value.copy(
                    firstName= event.firsName
                )
                printState()
            }

            is UIEventRegistro.EmailChanged -> {
                registrationIUState.value = registrationIUState.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEventRegistro.PasswordChanged -> {
                registrationIUState.value = registrationIUState.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEventRegistro.RegisterButtonClicked -> {
                registro()
            }

            is UIEventRegistro.PrivacyPolicyCheckBoxClicked -> {
                registrationIUState.value = registrationIUState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

        }

        validateDataWithRules()

    }

    private fun registro() {
        Log.d(TAG, "Inside_ signUp")
        printState()
        createUserInFireBase(
            email= registrationIUState.value.email,
            password = registrationIUState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult= Validator.validateName(
            fname= registrationIUState.value.firstName )

        val emailResult= Validator.validateEmail(
            email = registrationIUState.value.email )

        val passwordResult= Validator.validatePassword(
            password = registrationIUState.value.password )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationIUState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationIUState.value = registrationIUState.value.copy(
            nameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationPassed.value = fNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status
    }


    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationIUState.value.toString() )

    }

    private fun createUserInFireBase(email:String, password:String){
        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG,"IsSuccessful = ${it.isSuccessful}")

                signUpInProgress.value= false

                if(it.isSuccessful){
                    CineCritixAppRouter.navigateTo(Screen.Login)
                }


            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG,"Exception =${it.message}")
                Log.d(TAG,"Exception =${it.localizedMessage}")

            }

    }

    fun logout(){

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = AuthStateListener{
            if(it.currentUser==null){
                Log.d(TAG, "Inside Sign out is successful")
                CineCritixAppRouter.navigateTo(Screen.Login)
            }else{
                Log.d(TAG, "Inside Sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

}