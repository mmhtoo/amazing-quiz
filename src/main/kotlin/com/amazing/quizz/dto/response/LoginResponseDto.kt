package com.amazing.quizz.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class LoginResponseDto(
    val username : String,
    val email : String,
    val level : Int,
    val points : Int,

    @field:JsonFormat( pattern = "yyyy-mm:dd hh:mm:ss")
    val lastLoggedInDate : LocalDateTime,
    val followerCount : Int,
    val followingCount : Int,
    val token : String
)
