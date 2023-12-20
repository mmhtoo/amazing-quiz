package com.amazing.quizz.entitiy

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table( name = "quizzes" )
data class QuizzEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    val id : String,

    val title : String,

    val description : String? = null,

    val issuedAt : LocalDateTime? = null,

    val updatedAt : LocalDateTime? = null,

    val isPublic : Boolean? = false,

    val publicLink : String? = null,

    val shortCode : String? = null,

    val isDefault : Boolean = false,

    val isFree : Boolean? = false,

    @ManyToOne
    val category : QuizzCategoryEntity? = null,

    @ManyToOne
    val owner : AccountEntity? = null,
)
