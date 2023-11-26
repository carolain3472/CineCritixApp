package com.example.myapplication.data.viewModel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.LoginIUState
import com.example.myapplication.data.UIEventLogin
import com.example.myapplication.data.rules.Validator
import com.example.myapplication.navigation.CineCritixAppRouter
import com.example.myapplication.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {

    private val TAG= LoginViewModel::class.simpleName

    var loginIUState = mutableStateOf(LoginIUState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    val RC_SIGN_IN = 400

    val userLiveData = MutableLiveData<GoogleSignInAccount?>()

    var launcher: ActivityResultLauncher<Intent>? = null



    fun onEvent(event: UIEventLogin){
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

    fun login() {

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
                    CineCritixAppRouter.navigateTo(Screen.MainScreen)
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login:failure")
                Log.d(TAG, "${it.localizedMessage}")
                loginInProgress.value = false



            }

    }

    fun signInWithGoogle(activity: Activity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
        //handleSignInResult(signInIntent)

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.

    }




    fun handleSignInResult(data: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Aquí 'account' contiene la cuenta de Google
            // Puedes hacer lo que necesites con la cuenta (por ejemplo, obtener el nombre o el correo electrónico)
        } catch (e: ApiException) {
            // Aquí puedes manejar cualquier error que ocurra durante el inicio de sesión
            Log.e(TAG, "Error al iniciar sesión con Google: ${e.message}")
        }
    }

}