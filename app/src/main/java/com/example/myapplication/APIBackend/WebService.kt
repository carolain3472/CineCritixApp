package com.example.myapplication.APIBackend

import com.example.myapplication.data.request.UserEliminarCuenta
import com.example.myapplication.data.request.UserEnviarIcono
import com.example.myapplication.data.response.UploadResponse
import com.example.myapplication.data.request.UserInfo
import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.request.UserLogin
import com.example.myapplication.data.response.UserLoginResponse
import com.example.myapplication.data.request.UserRegister
import com.example.myapplication.data.request.UserResetContrasena
import com.example.myapplication.data.request.UserResetContrasenaEmail
import com.example.myapplication.data.request.UserUpdateContrasena
import com.example.myapplication.data.request.UserUpdateDatos
import com.example.myapplication.data.request.UserUpdateImage
import com.example.myapplication.data.response.UserEliminarCuentaResponse
import com.example.myapplication.data.response.UserEnviarIconoResponse
import com.example.myapplication.data.response.UserResetContrasenaEmailResponse
import com.example.myapplication.data.response.UserResetContrasenaResponse
import com.example.myapplication.data.response.UserResponse
import com.example.myapplication.data.response.UserUpdateImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface WebService {

    @POST("users/register/")
    suspend fun register(
        @Body usuario: UserRegister
    ): Response<UserResponse>

    @POST("users/login/")
    suspend fun login(
        @Body usuario: UserLogin
    ): Response<UserLoginResponse>


    @POST("users/update-profile/")
    suspend fun uploadImage(
        @Body usuario: UserUpdateImage,
    ): Response<UserUpdateImageResponse>

    @POST("users/quitar-profile/")
    suspend fun deleteImage(
        @Body usuario: UserInfo,
    ): Response<UserUpdateImageResponse>


    @POST("users/enviar-icono/")
    suspend fun enviarIcono(
        @Body usuario: UserEnviarIcono,
    ): Response<UserEnviarIconoResponse>


    //@Multipart
    //@POST("users/update-profile/")
    //suspend fun uploadImage(
    //    @Part("email") email: RequestBody,
    //    @Part image: MultipartBody.Part
    //): Response<UserUpdateImageResponse>

    @POST("users/obtener-informacion/")
    suspend fun getInfo(
        @Body usuario: UserInfo
    ): Response<UserInfoResponse>


    @POST("users/update-datos-basicos/")
    suspend fun updateDatos(
        @Body usuario: UserUpdateDatos
    ): Response<UserResponse>

    @POST("users/update_contra/")
    suspend fun updateContrasena(
        @Body usuario: UserUpdateContrasena
    ): Response<UserResponse>

    @POST("users/logout/")
    suspend fun logout(
        @Body usuario: UserInfo
    ): Response<UserResponse>


    @POST("api/password_reset/")
    suspend fun passwordReset(
        @Body usuario: UserResetContrasenaEmail
    ): Response<UserResetContrasenaEmailResponse>

    @POST("users/validate_token/")
    suspend fun newPassword(
        @Body usuario: UserResetContrasena
    ): Response<UserResetContrasenaResponse>


    @POST("users/darme-de-baja/")
    suspend fun eliminarCuenta(
        @Body usuario: UserEliminarCuenta
    ): Response<UserEliminarCuentaResponse>







}