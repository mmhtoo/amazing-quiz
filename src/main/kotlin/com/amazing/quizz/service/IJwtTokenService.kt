package com.amazing.quizz.service

import com.auth0.jwt.interfaces.DecodedJWT
import java.util.Date

interface IJwtTokenService {

    fun generate(payload : Map<String,String>,expiredAt : Date) : String

    fun decodeToken(token: String) : DecodedJWT

    fun isExpired(expiredAt: Date) : Boolean

    fun isValid(token: String) : Boolean

}