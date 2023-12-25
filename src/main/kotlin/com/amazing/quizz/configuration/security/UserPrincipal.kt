package com.amazing.quizz.configuration.security

import com.amazing.quizz.entitiy.AccountEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal (
    private val account : AccountEntity
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            SimpleGrantedAuthority("USER")
        )
    }

    override fun getPassword(): String {
        return account.password
    }

    override fun getUsername(): String {
        return account.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    // TO DO : linked with verified
    override fun isEnabled(): Boolean {
        return true
    }

    fun getUser() : AccountEntity {
        return account
    }

}