package com.amazing.quizz.service

import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.entitiy.AccountEntity

interface IAccountService {

    fun createNewAccount(dto: RegisterAccountDto) : AccountEntity

    fun loginAccount(email: String, password : String) : String

//    fun getAccountByEmail(email: String) : AccountEntity

    fun isEmailDuplicate(email: String) : Boolean

    fun isEmailDuplicate(email: String, userId: String) : Boolean

}