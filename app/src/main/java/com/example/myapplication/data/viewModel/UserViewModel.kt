package com.example.myapplication.data.viewModel

import android.net.Uri
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
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.data.ImageCallBack
import com.example.myapplication.data.request.UserUpdateImage
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Response
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

var TAG2 = "DATOS"
class UserViewModel : ViewModel() {

    val _uiStateInfo = mutableStateOf(UserInfo())
    val _uiStateupdateDatos = mutableStateOf(UserUpdateDatos())
    val _uiStateupdateContrasena = mutableStateOf(UserUpdateContrasena())
    val _uiStateeliminarCuenta = mutableStateOf(UserEliminarCuenta())
    val _uiStateupdateImage = mutableStateOf(UserUpdateImage())

    fun setNewPassword(pass: String){
        _uiStateupdateContrasena.value = _uiStateupdateContrasena.value.copy(
            contrasena = pass
        )
    }

    fun setImage(image: ByteArrayOutputStream){
        _uiStateupdateImage.value = _uiStateupdateImage.value.copy(
            imagen_seleccionada = image
        )
    }

    fun setEmail(email: String){
        _uiStateupdateImage.value = _uiStateupdateImage.value.copy(
            email = email
        )
    }


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

    fun uploadImage(callback: ImageCallBack, imagePath: Uri?, context:Context) {


        val email = _userLoginResponse.value?.body()?.user_email

        val contentResolver: ContentResolver = context.contentResolver
        val bytes= imagePath?.let {
            contentResolver.openInputStream(it).use {
                it?.readBytes() ?: null

            }
        }

        val bitmap: Bitmap? = bytes?.let {
            BitmapFactory.decodeStream(ByteArrayInputStream(it))
        }

        if (email != null) {
            setEmail(email)
        }


        val outputStream = ByteArrayOutputStream()
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        }
        val imageBytes: ByteArray = outputStream.toByteArray()

        val requestFile = RequestBody.create("image/png".toMediaTypeOrNull(), imageBytes)
        val imagePart = MultipartBody.Part.createFormData("image", "image.png", requestFile)

        setImage(outputStream)

        val textRequestBody = email?.let { RequestBody.create(MultipartBody.FORM, it) }



        Log.d(TAG2, "IMAGEEEEN")
        Log.d(TAG2, textRequestBody.toString())
        Log.d(TAG2, imagePart.toString())


        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response =
                    textRequestBody?.let { RetrofitClient.webService.uploadImage(email= it, imagePart) }

                if (response != null) {
                    Log.d(TAG2, response.code().toString())
                }

                if (response != null) {
                    if (response.isSuccessful) {
                        val uploadResponse = response.body()
                        // Manejar la respuesta del servidor segÃºn tus necesidades
                        Log.d(TAG2, "Se guardo")
                        Log.d(TAG2, uploadResponse.toString())
                    } else {
                        // Manejar errores de la respuesta del servidor
                        if (response != null) {
                            Log.d(TAG2, "Error responseee: ${response.code()}")
                        }
                    }
                }


                withContext(Dispatchers.Main) {
                    if (bitmap != null) {
                        callback.onImageResult(bitmap)
                    }

                }

            } catch (e: Exception) {
                // Manejar excepciones
                Log.d(TAG2, "Error aaa responseee: ${e.message}")
            }
        }


        // Hacer la solicitud al servidor

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