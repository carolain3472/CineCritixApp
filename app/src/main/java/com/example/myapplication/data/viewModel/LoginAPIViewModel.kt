package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.LoginCallBack
import com.example.myapplication.data.request.UserLogin
import com.example.myapplication.data.response.UserLoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

var TAG1 = "LOGIN"
class LoginAPIViewModel: ViewModel() {

    val _uiStateLogin = mutableStateOf(UserLogin())


    fun setEmail(email: String){
        _uiStateLogin.value = _uiStateLogin.value.copy(
            email = email
        )
    }

    fun setContrasena(contrasena: String){
        _uiStateLogin.value = _uiStateLogin.value.copy(
            contrasena = contrasena
        )
    }

    fun login(callback: LoginCallBack){

        Log.d(TAG, "Hola")
        Log.d(TAG, _uiStateLogin.value.email)
        Log.d(TAG, _uiStateLogin.value.contrasena)

        viewModelScope.launch(Dispatchers.IO) {
            try {

                Log.d(TAG1, "Hola IO")

                val response: Response<UserLoginResponse> = RetrofitClient.webService.login(_uiStateLogin.value)

                Log.d(TAG1, response.code().toString())
                //Log.d(TAG1, response.body()?.valid.toString())
                //Log.d(TAG1, response.body()?.token.toString())

                if ( response.body()?.valid ?: false && response.isSuccessful && response.code() == 200){
                    val userLoginResponse: UserLoginResponse? = response.body()

                    // Verificar si el cuerpo no es nulo antes de utilizarlo
                    if (userLoginResponse != null) {
                        Log.d(TAG1, userLoginResponse.toString())
                        // Aquí puedes acceder a los datos en userLoginResponse

                    }
                    withContext(Dispatchers.Main) {
                        callback.onViewModelResult(response)
                        callback.onLoginResult(true)

                    }


                }else{
                    Log.d(TAG, "no 200")
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.errorBody().toString())
                    callback.onLoginResult(false)

                }

            }catch (e: Exception){
                Log.d(TAG, "Nonono")
                Log.e(TAG, "Error en la solicitud de inicio de sesión: ${e.message}")
                callback.onLoginResult(false)

            }

        }
    }

}