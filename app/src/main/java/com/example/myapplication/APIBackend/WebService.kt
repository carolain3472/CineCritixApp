package com.example.myapplication.APIBackend

import com.example.myapplication.data.UserRegister
import com.example.myapplication.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {

    @POST("/register")
    suspend fun register(
        @Body usuario: UserRegister
    ): Response<UserResponse>



}