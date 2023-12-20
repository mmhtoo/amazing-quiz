package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity,String> {
}