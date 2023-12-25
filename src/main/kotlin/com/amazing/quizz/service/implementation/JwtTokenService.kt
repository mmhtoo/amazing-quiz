package com.amazing.quizz.service.implementation

import com.amazing.quizz.service.IJwtTokenService
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtTokenService (
    private val algorithm : Algorithm
) : IJwtTokenService {

    @Value("\${jwt.issuer}")
    private val ISSUER : String = "ISSUER"

    override fun generate(payload : Map<String,String>, expiredAt : Date): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withExpiresAt(expiredAt)
            .withPayload(payload)
            .sign(algorithm)
    }

    override fun decodeToken(token: String): DecodedJWT {
        try {
            val verifier : JWTVerifier = JWT.require(algorithm)
                .build()
            return verifier.verify(token)
        }catch(e : JWTVerificationException){
            throw Exception("Invalid token!")
        }
    }

    override fun isValid(token: String): Boolean {
        val decodedJWT = decodeToken(token)
        return !isExpired(decodedJWT.expiresAt) && decodedJWT.issuer == ISSUER
    }

    override fun isExpired(expiredAt: Date) : Boolean = expiredAt.before(Date())

}