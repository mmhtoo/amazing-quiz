package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "account_follows" )
data class AccountFollowEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val id : Int? = null,

    val fromAccount : String,

    val toAccount : String,

    val issuedAt : LocalDateTime? = null,
)
