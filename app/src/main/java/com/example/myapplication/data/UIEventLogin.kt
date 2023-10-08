package com.example.myapplication.data

sealed class UIEventLogin {

    data class EmailChanged(val email:String) : UIEventLogin()
    data class PasswordChanged(val password:String) : UIEventLogin()

    object LoginButtonClicked: UIEventLogin()
}
