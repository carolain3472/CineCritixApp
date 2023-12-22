package com.example.myapplication.data

import com.example.myapplication.data.viewModel.Actor
import com.example.myapplication.data.viewModel.Movie

interface actoresCallBack {
    fun onActoresResult(success:  MutableList<Actor>)
}