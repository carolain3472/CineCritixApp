package com.example.myapplication.data

sealed class UIEvent {
    data class FirstNameChanged(val firsName:String) : UIEvent()
    data class EmailChanged(val email:String) : UIEvent()
    data class PasswordChanged(val password:String) : UIEvent()

    object RegisterButtonClicked: UIEvent()
}
