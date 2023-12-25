package com.amazing.quizz.service

import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.entitiy.AccountEntity
import java.util.Date

interface IAccountService {

    fun createNewAccount(dto: RegisterAccountDto) : AccountEntity

    fun loginAccount(email: String, password : String) : AccountEntity

    fun isEmailDuplicate(email: String) : Boolean

    fun isEmailDuplicate(email: String, userId: String) : Boolean

    fun getTokenExpiredDate() : Date

}