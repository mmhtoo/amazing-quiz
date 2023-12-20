package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.QuizzSubmitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzSubmitRepository : JpaRepository<QuizzSubmitEntity,String> {
}