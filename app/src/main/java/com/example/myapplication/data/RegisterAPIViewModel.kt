package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.Response

var TAG = "REGISTRO"
class RegisterAPIViewModel: ViewModel() {

    val _uiStateRegister = mutableStateOf(UserRegister())

    fun setNombre(nombre: String){
        _uiStateRegister.value = _uiStateRegister.value.copy(
            nombre = nombre
        )
    }

    fun setDocumento(documento: String){
        _uiStateRegister.value = _uiStateRegister.value.copy(
            documento = documento
        )
    }

    fun setEmail(email: String){
        _uiStateRegister.value = _uiStateRegister.value.copy(
            email = email
        )
    }

    fun setContrasena(contrasena: String){
        _uiStateRegister.value = _uiStateRegister.value.copy(
            contrasena = contrasena
        )
    }

    fun register(callback: RegisterCallback){

        Log.d(TAG, "Hola")
        Log.d(TAG, _uiStateRegister.value.nombre)
        Log.d(TAG, _uiStateRegister.value.documento)
        Log.d(TAG, _uiStateRegister.value.email)
        Log.d(TAG, _uiStateRegister.value.contrasena)

        viewModelScope.launch(Dispatchers.IO) {
            try {

                Log.d(TAG, "Hola IO")

                val response: Response<UserResponse> = RetrofitClient.webService.register(_uiStateRegister.value)
                Log.d(TAG, response.code().toString())
                Log.d(TAG, response.body()?.valid.toString())
                if (response.isSuccessful && response.code() == 200){
                    withContext(Dispatchers.Main) {
                        callback.onRegisterResult(true)
                    }

                }else{
                    Log.d(TAG, "no 200")
                    Log.d(TAG, response.code().toString())
                    callback.onRegisterResult(false)
                }

            }catch (e: Exception){
                Log.d(TAG, "Nonono")
                Log.e(TAG, "Error en la solicitud de inicio de sesión: ${e.message}")
                callback.onRegisterResult(false)

            }

        }
    }




}