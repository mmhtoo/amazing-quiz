package com.amazing.quizz.entitiy

import jakarta.persistence.*

@Entity
@Table( name = "quizz_items" )
data class QuizzItemEntity(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    val id : Int? = null,

    val question : String,

    @ManyToOne
    val quizz : QuizzEntity,

    /*
     * this is json string value represent for quizz question
     */
    val answer : String,
)
