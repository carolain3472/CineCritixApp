package com.example.myapplication.data.request

import java.io.File


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext




data class UserUpdateImage(
    val email: String="",
    val imagen_seleccionada: File
)