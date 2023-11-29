package com.example.myapplication.data.request

import java.io.ByteArrayOutputStream

data class UserUpdateImage(
    val email: String="",
    val imagen_seleccionada: ByteArrayOutputStream? = null,
)
