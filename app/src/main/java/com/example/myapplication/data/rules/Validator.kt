package com.example.myapplication.data.rules

import java.util.regex.Pattern

object Validator {

    fun validateName(fname:String) :ValidationResult{
        return ValidationResult(
            (!fname.isNullOrEmpty() &&  fname.length>=2)
        )
    }
    fun validateApellido(apellido:String) :ValidationResult{
        return ValidationResult(
            (!apellido.isNullOrEmpty())
        )
    }

    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password:String): ValidationResult{
        val minLength = 6
        val specialCharacterPattern = Pattern.compile("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")
        val commonPasswords = listOf("123456", "password", "qwerty", "123456789", "12345678", "987654321", "contraseña1234", "contraseña123")

        val hasUpperCase = password.any { it.isUpperCase() }
        val hasNumber = password.any { it.isDigit() }

        return if (password.length >= minLength
            && specialCharacterPattern.matcher(password).find()
            && hasUpperCase
            && hasNumber
            && !commonPasswords.contains(password.toLowerCase())
        ) {
            ValidationResult(true)
        } else {
            ValidationResult(false)
        }
    }

    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }

    fun validateDoc(doc:String) :ValidationResult{
        return ValidationResult(
            (!doc.isNullOrEmpty() &&  doc.length>=5)
        )
    }


}

data class ValidationResult(
    val status: Boolean = false
)