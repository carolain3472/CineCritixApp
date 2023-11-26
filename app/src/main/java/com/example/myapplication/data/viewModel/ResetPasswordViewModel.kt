package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.request.UserInfo
import com.example.myapplication.data.request.UserResetContrasena
import com.example.myapplication.data.request.UserResetContrasenaEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


var TAG6 = "RESET"
class ResetPasswordViewModel: ViewModel(){

    val _uiStateResetPassword = mutableStateOf(UserResetContrasenaEmail())
    val _uiStateNewPassword = mutableStateOf(UserResetContrasena())

    fun setEmail(email: String){
        _uiStateResetPassword.value = _uiStateResetPassword.value.copy(
            email = email
        )
    }
    fun setNewPasswordEmail(email: String){
        _uiStateNewPassword.value = _uiStateNewPassword.value.copy(
            email = email,
        )
    }
    fun setNewPasswordToken(token:String){
        _uiStateNewPassword.value = _uiStateNewPassword.value.copy(

            token = token,

        )
    }
    fun setNewPassword( password:String){
        _uiStateNewPassword.value = _uiStateNewPassword.value.copy(

            password=password
        )
    }

    fun ResetPassword(){
        viewModelScope.launch(Dispatchers.IO) {
            try {


                val response = RetrofitClient.webService.passwordReset(_uiStateResetPassword.value)

                Log.d(TAG6, response.code().toString())
                if (response.isSuccessful) {
                    val uploadResponse = response.body()
                    // Manejar la respuesta del servidor según tus necesidades
                    Log.d(TAG6, "Se guardo")
                    Log.d(TAG6, uploadResponse.toString())
                } else {
                    // Manejar errores de la respuesta del servidor
                    Log.d(TAG6, "Error en la carga: ${response.message()}")
                }
            } catch (e: Exception) {
                // Manejar excepciones
                Log.d(TAG6, "Error en la carga: ${e.message}")
            }
        }
    }

    fun NewPassword(){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG6, _uiStateNewPassword.value.email)
                Log.d(TAG6, _uiStateNewPassword.value.token)
                Log.d(TAG6, _uiStateNewPassword.value.password)

                val response = RetrofitClient.webService.newPassword(_uiStateNewPassword.value)
                val uploadResponse = response.body()

                Log.d(TAG6, uploadResponse.toString())

                Log.d(TAG6, response.code().toString())



                if (response.isSuccessful) {

                    withContext(Dispatchers.Main) {



                    }

                    // Manejar la respuesta del servidor según tus necesidades
                    Log.d(TAG6, "Se guardo")

                } else {
                    // Manejar errores de la respuesta del servidor
                    Log.d(TAG6, "Error en la carga: ${response.code()}")
                }
            } catch (e: Exception) {
                // Manejar excepciones
                Log.d(TAG6, "Error en la carga: ${e.message}")
            }
        }

    }

}