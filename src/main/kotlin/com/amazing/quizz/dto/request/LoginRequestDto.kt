package com.amazing.quizz.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank( message = "{email.notBlank}" )
    val email : String,

    @field:NotBlank( message = "{password.notBlank}" )
    val password : String
)
