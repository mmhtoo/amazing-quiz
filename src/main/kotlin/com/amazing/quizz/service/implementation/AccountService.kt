package com.amazing.quizz.service.implementation

import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.entitiy.AccountEntity
import com.amazing.quizz.repository.AccountRepository
import com.amazing.quizz.service.IAccountService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AccountService (
   private val accountRepo : AccountRepository,
   private val passwordEncoder : PasswordEncoder
) : IAccountService {

    override fun createNewAccount(dto: RegisterAccountDto): AccountEntity {
        // checking duplication email for newly account
        if(isEmailDuplicate(dto.email)){
            throw Exception("This email is already used by another account!")
        }
        val accountEntity = AccountEntity(
            username = dto.username,
            email = dto.email,
            points = 10,
            level = 1,
            createdAt = LocalDateTime.now(),
            followerCount = 0,
            followingCount = 0,
            password = passwordEncoder.encode(dto.password),
            hasVerified = false
        )
        return accountRepo.save(accountEntity)
    }

    override fun loginAccount(email: String, password: String): String {
        return email
    }

//    override fun getAccountByEmail(email: String): AccountEntity {
//    }

    override fun isEmailDuplicate(email: String): Boolean {
        return accountRepo.findByEmail(email).isPresent
    }

    override fun isEmailDuplicate(email: String, userId: String): Boolean {
        TODO("Not yet implemented")
    }

}