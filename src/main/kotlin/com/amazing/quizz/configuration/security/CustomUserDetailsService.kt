package com.amazing.quizz.configuration.security

import com.amazing.quizz.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService (
    val accountRepo : AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepo.findByEmail(username)
            .orElseThrow {
                UsernameNotFoundException("Invalid username or password!")
            }
        return UserPrincipal(account)
    }

}