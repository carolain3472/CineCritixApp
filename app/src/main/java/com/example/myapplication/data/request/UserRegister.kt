package com.example.myapplication.data.request

data class UserRegister(
    var nombre: String = "",
    var apellido: String = "",
    var documento: String = "",
    var email: String = "",
    var contrasena: String = ""
)
