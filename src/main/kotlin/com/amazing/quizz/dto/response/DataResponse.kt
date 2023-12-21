package com.amazing.quizz.dto.response

data class DataResponse(
    val responseDescription: String,
    val status : Int,
    val data : Any?
)
