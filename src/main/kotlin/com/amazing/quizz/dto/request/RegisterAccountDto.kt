package com.amazing.quizz.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class RegisterAccountDto(
    @field:NotBlank( message = "{username.notBlank}" )
    @field:Length( min = 3 , max = 20 , message = "{username.range}" )
    val username : String,

    @field:NotBlank( message = "{email.notBlank}" )
    @field:Length( min = 5 , max = 20 , message = "{email.range}" )
    @field:Email( message = "{email.invalidFormat}" )
    val email : String,

    @field:NotBlank( message = "{password.notBlank}" )
    @field:Length( min = 6  , max = 20 , message = "{password.range}" )
    val password : String
)
