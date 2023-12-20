package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "quizz_categories" )
data class QuizzCategoryEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val id : Int? = null,

    val name : String,

    val createdAt : LocalDateTime? = null,

    val updatedAt : LocalDateTime? = null,
)
