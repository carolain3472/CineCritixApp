package com.example.myapplication.data.rules

object Validator {

    fun validateName(fname:String) :ValidationResult{
        return ValidationResult(
            (!fname.isNullOrEmpty() &&  fname.length>=6)
        )
    }

    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password:String): ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=4)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)