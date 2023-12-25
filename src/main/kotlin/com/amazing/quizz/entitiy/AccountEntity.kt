package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "accounts" )
data class AccountEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    val id : String? = null,

    val username : String,

    val email: String,

    val password : String,

    val createdAt : LocalDateTime? = null,

    val updatedAt : LocalDateTime? = null,

    val lastLoggedInDate : LocalDateTime? = null,

    val level : Int = 0,

    val points : Int = 0,

    val followerCount : Int? = 0,

    val followingCount : Int? = 0,

    val profileImage : String? = null,

    val hasVerified : Boolean
)