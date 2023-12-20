package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.QuizzEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzRepository : JpaRepository<QuizzEntity,String> {
}