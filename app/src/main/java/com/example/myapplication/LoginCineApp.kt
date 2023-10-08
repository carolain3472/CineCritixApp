package com.example.myapplication

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginCineApp : Application(){

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }

}