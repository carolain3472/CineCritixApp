package com.example.myapplication.data

sealed class UIEventRegistro {
    data class FirstNameChanged(val firsName:String) : UIEventRegistro()
    data class EmailChanged(val email:String) : UIEventRegistro()
    data class PasswordChanged(val password:String) : UIEventRegistro()

    data class DocumentChanged(val document:String): UIEventRegistro()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : UIEventRegistro()
    object RegisterButtonClicked: UIEventRegistro()
}
