package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.VerificationCodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VerificationCodeRepository : JpaRepository<VerificationCodeEntity,Int> {
}