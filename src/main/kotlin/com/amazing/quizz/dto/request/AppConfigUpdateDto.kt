package com.amazing.quizz.dto.request

import org.hibernate.validator.constraints.Range

data class AppConfigUpdateDto(
    @field:Range( min = 1, max = 100, message = "{rewardPointPerQuizz.range}" )
    val rewardPointPerQuizz : Int,

    @field:Range( min = 1, max = 100, message = "{rewardExpPerQuizz.range}" )
    val rewardExpPerQuizz : Int,

    @field:Range( min = 1, max = 100, message = "{requiredPointPerQuizz.range}" )
    val requiredPointPerQuizz : Int,
)
