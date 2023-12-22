package com.example.myapplication.data

import androidx.compose.runtime.MutableState
import com.example.myapplication.data.viewModel.MovieSelected

interface MovieCallBack {

    fun onMovieResult(success: MovieSelected)
}