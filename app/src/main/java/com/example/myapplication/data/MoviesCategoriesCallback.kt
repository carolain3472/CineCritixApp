package com.example.myapplication.data

import com.example.myapplication.data.viewModel.Movie

interface MoviesCategoriesCallback {
    fun onMovieResult(success:  MutableList<Movie>)
}