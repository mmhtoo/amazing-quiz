package com.amazing.quizz.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

   @Bean
   fun securityFilterChain(http : HttpSecurity) : SecurityFilterChain {
       http.csrf { it.disable() }
           .cors {  }
           .authorizeHttpRequests {
               it.anyRequest().permitAll()
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

}