package com.example.myapplication.data

import android.graphics.Bitmap

interface ImageCallBack {
    fun onImageResult(success: Bitmap)
}