package com.amazing.quizz.service.implementation

import com.amazing.quizz.configuration.security.UserPrincipal
import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.entitiy.AccountEntity
import com.amazing.quizz.repository.AccountRepository
import com.amazing.quizz.service.IAccountService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class AccountService (
   private val accountRepo : AccountRepository,
   private val passwordEncoder : PasswordEncoder,
   private val authenticationManager: AuthenticationManager
) : IAccountService {

    @Value("\${jwt.lifetime}")
    private val AUTH_LIFE : Int = 1

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

    override fun loginAccount(email: String, password: String): AccountEntity {
        val authentication : Authentication =  authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email,password)
        )

        SecurityContextHolder.getContext()
            .authentication = authentication

        return (authentication.principal as UserPrincipal).getUser()
    }

    override fun isEmailDuplicate(email: String): Boolean {
        return accountRepo.findByEmail(email).isPresent
    }

    override fun isEmailDuplicate(email: String, userId: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTokenExpiredDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, AUTH_LIFE)
        return calendar.time
    }

}