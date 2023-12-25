package com.amazing.quizz.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig (
    private val userDetailsService: UserDetailsService
) {

   @Bean
   fun securityFilterChain(http : HttpSecurity) : SecurityFilterChain {
       http.csrf { it.disable() }
           .cors {  }
           .sessionManagement {
               it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           }
           .authorizeHttpRequests {
               it.requestMatchers("/api/v1/sign-in","/api/v1/sign-up").permitAll()
                   .anyRequest().permitAll()
           }
           .formLogin {
               it.disable()
           }
       return http.build()
   }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun daoAuthenticationProvider() : AuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userDetailsService)
        return daoAuthenticationProvider
    }

    @Bean
    fun authenticationManager() : AuthenticationManager = ProviderManager(daoAuthenticationProvider())

}