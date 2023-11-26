package com.example.myapplication.data.rules

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
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=4)
        )
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