package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.request.UserInfo
import com.example.myapplication.data.request.UserUpdateContrasena
import com.example.myapplication.data.request.UserUpdateDatos
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.response.UserLoginResponse
import com.example.myapplication.screens.TAG3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

var TAG2 = "DATOS"
class UserViewModel : ViewModel() {

    val _uiStateInfo = mutableStateOf(UserInfo())
    val _uiStateupdateDatos = mutableStateOf(UserUpdateDatos())
    val _uiStateupdateContrasena = mutableStateOf(UserUpdateContrasena())

    private val _userLoginResponse = MutableLiveData<Response<UserLoginResponse>?>()
    val userLoginResponse: LiveData<Response<UserLoginResponse>?> = _userLoginResponse

    fun setUserLoginResponse(response: Response<UserLoginResponse>?) {
        _userLoginResponse.value = response
    }

    private val _userInfoResponse = MutableLiveData<Response<UserInfoResponse>?>()
    val userInfoResponse: LiveData<Response<UserInfoResponse>?> = _userInfoResponse

    fun setUserInfoResponse(response: Response<UserInfoResponse>?) {
        _userInfoResponse.value = response
    }

    fun uploadImage(imagePath: String) {

        val file = File(imagePath)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart =
            MultipartBody.Part.createFormData("imagen_seleccionada", file.name, requestFile)

        val userEmail = _userLoginResponse.value?.body()?.user_email

        // Hacer la solicitud al servidor
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.uploadImage(userEmail, imagePart)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    val uploadResponse = response.body()
                    // Manejar la respuesta del servidor según tus necesidades
                    Log.d(TAG2, "Se guardo")
                    Log.d(TAG2, uploadResponse.toString())
                } else {
                    // Manejar errores de la respuesta del servidor
                    Log.d(TAG2, "Error en la carga: ${response.code()}")
                }
            } catch (e: Exception) {
                // Manejar excepciones
                Log.d(TAG2, "Error en la carga: ${e.message}")
            }
        }
    }

    fun getInfo(){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateInfo.value = _uiStateInfo.value.copy(
                email =  userEmail
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getInfo(_uiStateInfo.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG2, response.body().toString())
                    withContext(Dispatchers.Main) {
                        setUserInfoResponse(response)
                    }


                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }
    }

    fun updateDatos(name:String, lastname:String){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateupdateDatos.value = _uiStateupdateDatos.value.copy(
                email =  userEmail,
                nombre= name,
                apellido=lastname
            )


        }


        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (userEmail != null) {
                    Log.d("TAG3", userEmail)
                }
                val response = RetrofitClient.webService.updateDatos(_uiStateupdateDatos.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG3, "Datos Actualizados")

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }

    }


    fun updateContrasena(contra: String){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateupdateContrasena.value = _uiStateupdateContrasena.value.copy(
                email =  userEmail,
                contrasena = contra
            )


        }


        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (userEmail != null) {
                    Log.d("TAG3", userEmail)
                }
                val response = RetrofitClient.webService.updateContrasena(_uiStateupdateContrasena.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG3, "Datos Actualizados")

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }

    }


    fun logout(){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateInfo.value = _uiStateInfo.value.copy(
                email =  userEmail
            )


        }


        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (userEmail != null) {
                    Log.d("TAG3", userEmail)
                }
                val response = RetrofitClient.webService.logout(_uiStateInfo.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG3, "Logout")

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }

    }
}