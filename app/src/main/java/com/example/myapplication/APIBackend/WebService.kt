package com.example.myapplication.APIBackend

import com.example.myapplication.data.UserLogin
import com.example.myapplication.data.UserLoginResponse
import com.example.myapplication.data.UserRegister
import com.example.myapplication.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {

    @POST("users/register/")
    suspend fun register(
        @Body usuario: UserRegister
    ): Response<UserResponse>

    @POST("users/login/")
    suspend fun login(
        @Body usuario: UserLogin
    ): Response<UserLoginResponse>

}