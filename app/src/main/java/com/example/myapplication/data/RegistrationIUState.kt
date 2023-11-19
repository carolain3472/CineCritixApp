package com.example.myapplication.data

data class RegistrationIUState (

    var firstName :String= "",
    var email :String="",
    var password :String="",
    var doc: String = "",
    var privacyPolicyAccepted :Boolean =false,

    var nameError :Boolean = false,
    var emailError :Boolean =false,
    var passwordError :Boolean= false,
    var docError: Boolean = false,
    var privacyPolicyError:Boolean =false

)