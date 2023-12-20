package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.QuizzCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzCategoryRepository : JpaRepository<QuizzCategoryEntity,Int> {
}