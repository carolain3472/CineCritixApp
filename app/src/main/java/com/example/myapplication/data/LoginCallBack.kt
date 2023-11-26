package com.example.myapplication.data

import com.example.myapplication.data.response.UserLoginResponse
import retrofit2.Response

interface LoginCallBack {

    fun onViewModelResult(success: Response<UserLoginResponse>)

    fun onLoginResult(success: Boolean)


}