package com.amazing.quizz.dto.response

data class ErrorResponse(
    val responseDescription : String,
    val status : Int,
    val errors : Map<String,String?>
)
