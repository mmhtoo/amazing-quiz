package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "app_configs" )
data class AppConfigEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val id : Int? = null,

    val rewardPointPerQuizz : Int,

    val requiredPointPerQuizz : Int,

    val rewardExpPerQuizz : Int,

    var issuedAt : LocalDateTime? = null,

    val updatedAt : LocalDateTime? = null,
)
