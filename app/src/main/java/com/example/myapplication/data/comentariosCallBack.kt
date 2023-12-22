package com.example.myapplication.data

import com.example.myapplication.data.viewModel.Actor
import com.example.myapplication.data.viewModel.Comentario

interface comentariosCallBack {
    fun onComentariosResult(success:  MutableList<Comentario>)
}