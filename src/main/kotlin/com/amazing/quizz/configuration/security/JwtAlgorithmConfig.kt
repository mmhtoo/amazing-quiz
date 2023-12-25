package com.amazing.quizz.configuration.security

import com.auth0.jwt.algorithms.Algorithm
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Configuration
class JwtAlgorithmConfig {

    @Bean
    fun algorithm() : Algorithm {
        val keyFactory = KeyPairGenerator.getInstance("RSA")
        keyFactory.initialize(1024)
        val keyPair = keyFactory.genKeyPair()
        return Algorithm
            .RSA256(keyPair.public as RSAPublicKey, keyPair.private as RSAPrivateKey)
    }

}