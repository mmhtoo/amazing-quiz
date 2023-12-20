package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "verification_codes" )
data class VerificationCodeEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val id : Int,

    val code : String,

    val issuedAt : LocalDateTime? = null,

    val confirmedAt : LocalDateTime? = null,

    val expiredAt : LocalDateTime? = null,

    @ManyToOne
    @JoinColumn( name = "account_id" )
    val account : AccountEntity
)
