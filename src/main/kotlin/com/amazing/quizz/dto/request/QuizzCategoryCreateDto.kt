package com.amazing.quizz.dto.request

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class QuizzCategoryCreateDto (
    @field:NotBlank( message = "{quizzCategory.notBlank}" )
    @field:Length( message = "{quizzCategory.range}" )
    val name : String
)