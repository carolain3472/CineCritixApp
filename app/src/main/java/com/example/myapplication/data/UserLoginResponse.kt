package com.example.myapplication.data

import android.util.JsonReader

data class UserLoginResponse (

    var valid: Boolean,
    var token: String,
    var user_id: Int,
    var user_documento: String,
    var user_nombre: String,
    var user_email:String

)



