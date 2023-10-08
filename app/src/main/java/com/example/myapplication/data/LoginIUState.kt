package com.example.myapplication.data

data class LoginIUState (

    var email :String="",
    var password :String="",

    var emailError :Boolean =false,
    var passwordError :Boolean= false

)