package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "quizz_submits" )
data class QuizzSubmitEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    val id : String? = null,

    @ManyToOne
    val submitter : AccountEntity,

    @ManyToOne
    val quizz : QuizzEntity,

    val hasCompleted : Boolean = false,

    val issuedAt : LocalDateTime? = null,

    val updatedAt : LocalDateTime? = null,

    val completedPercent : Double = 0.0,

    /*
     * when cancelled quizz, if user will choose save progress, it will be
     * assumed as tempSubmitCount 1
     */
    val tempSubmitCount : Int = 0,
)
