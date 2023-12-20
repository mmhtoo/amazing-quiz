package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.AccountFollowEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountFollowRepository : JpaRepository<AccountFollowEntity,Int> {
}