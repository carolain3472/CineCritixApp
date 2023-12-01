package com.example.myapplication.data

import com.example.myapplication.data.response.UserInfoResponse
import com.example.myapplication.data.response.UserLoginResponse
import retrofit2.Response

interface CallBackInfoUser {

    fun onInfoResult(success: Response<UserInfoResponse>)
}