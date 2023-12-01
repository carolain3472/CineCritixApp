package com.example.myapplication.data.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.APIBackend.RetrofitClient
import com.example.myapplication.data.CodeCallBack
import com.example.myapplication.data.request.UserEliminarCuenta
import com.example.myapplication.data.request.UserInfo
import com.example.myapplication.data.request.UserUpdateContrasena
import com.example.myapplication.data.request.UserUpdateDatos
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.response.UserLoginResponse
import com.example.myapplication.screens.TAG3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import android.content.Context
import com.example.myapplication.data.CallBackInfoUser
import com.example.myapplication.data.ImagenURLCallBack
import com.example.myapplication.data.request.UserEnviarIcono
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Response
import java.io.File
import kotlin.io.encoding.ExperimentalEncodingApi

var TAG2 = "DATOS"
class UserViewModel : ViewModel() {

    val _uiStateInfo = mutableStateOf(UserInfo())
    val _uiStateupdateDatos = mutableStateOf(UserUpdateDatos())
    val _uiStateupdateContrasena = mutableStateOf(UserUpdateContrasena())
    val _uiStateeliminarCuenta = mutableStateOf(UserEliminarCuenta())
    val _uiStateenviarIcono = mutableStateOf(UserEnviarIcono())




    fun createTempImageFile(context: Context): File {

        val imageFileName = "temp_image_file"
        val fileExtension = ".jpg"

        return File(context.cacheDir, "$imageFileName$fileExtension")

    }




    fun setNewPassword(pass: String){
        _uiStateupdateContrasena.value = _uiStateupdateContrasena.value.copy(
            contrasena = pass
        )
    }

    /**fun setImage(image: String){
        _uiStateupdateImage.value = _uiStateupdateImage.value.copy(
            imagen_seleccionada = image
        )
    }

    fun setEmail(email: String){
        _uiStateupdateImage.value = _uiStateupdateImage.value.copy(
            email = email
        )
    }*/


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

    private val _urlImagen= MutableLiveData<String>()
    val urlImagen: LiveData<String>  = _urlImagen

    fun setUrlImage(imagenUrl: String ) {
        _urlImagen.value = imagenUrl
    }

    fun enviarIcono(name: String){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateenviarIcono.value = _uiStateenviarIcono.value.copy(
                email =  userEmail,
                icono = name
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.enviarIcono(_uiStateenviarIcono.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG2, response.body().toString())
                    withContext(Dispatchers.Main) {

                    }

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }






    }

    fun deleteImage(callback: ImagenURLCallBack){
        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateInfo.value = _uiStateInfo.value.copy(
                email =  userEmail
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.deleteImage(_uiStateInfo.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG2, response.body().toString())
                    withContext(Dispatchers.Main) {
                        response.body()?.let { callback.onImageURLResult(it.exito) }
                    }

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun uploadImage(callback: ImagenURLCallBack, file: File) {
        Log.d(TAG, "ACA ESTA EL NOMBRE DEL ARCHIVO")
        Log.d(TAG, file.length().toString())

        val userEmail: String = _userLoginResponse.value?.body()?.user_email?: ""

        val emailRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), userEmail)
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val imagePart = MultipartBody.Part.createFormData("imagen_seleccionada", file.name, requestFile)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.uploadImage(emailRequestBody, imagePart)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG2, response.body().toString())
                    withContext(Dispatchers.Main) {
                        response.body()?.let { callback.onImageURLResult(it.exito) }
                    }




                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }

    }




    fun getInfo(callBackInfoUser: CallBackInfoUser){

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
                        callBackInfoUser.onInfoResult(response)
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


    fun updateContrasena(callback: CodeCallBack){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateupdateContrasena.value = _uiStateupdateContrasena.value.copy(
                email =  userEmail
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
                    Log.d(TAG3, "Contraseña Actualizados")

                }else{
                    Log.d(TAG2, "Error en la carga: ${response.code()}")

                }

                withContext(Dispatchers.Main){
                    callback.onCodeResult(response.code())
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


    fun eliminarCuenta(){

        val userEmail = _userLoginResponse.value?.body()?.user_email

        if (userEmail!=null){
            _uiStateeliminarCuenta.value = _uiStateeliminarCuenta.value.copy(
                email =  userEmail
            )


        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (userEmail != null) {
                    Log.d("TAG3", userEmail)
                }
                val response = RetrofitClient.webService.eliminarCuenta(_uiStateeliminarCuenta.value)

                Log.d(TAG2, response.code().toString())
                if (response.isSuccessful) {
                    Log.d(TAG2, "Eliminar cuenta y logout")

                }else{
                    Log.d(TAG2, "Error en ELIMINAR: ${response.code()}")

                }


            }catch (e:Exception){
                Log.d(TAG2, "Error en la carga: ${e.message}")

            }

        }

    }

}