package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.QuizzItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizzItemRepository : JpaRepository<QuizzItemEntity,Int> {
}