package com.amazing.quizz.repository

import com.amazing.quizz.entitiy.AppConfigEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppConfigRepository : JpaRepository<AppConfigEntity,Int> {
}