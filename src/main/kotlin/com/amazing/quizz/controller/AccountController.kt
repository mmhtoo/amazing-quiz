package com.amazing.quizz.controller

import com.amazing.quizz.annotation.CheckValidation
import com.amazing.quizz.dto.request.LoginRequestDto
import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.dto.response.AppResponse
import com.amazing.quizz.dto.response.DataResponse
import com.amazing.quizz.dto.response.LoginResponseDto
import com.amazing.quizz.service.IAccountService
import com.amazing.quizz.service.implementation.JwtTokenService
import com.amazing.quizz.utility.StringUtil
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping( value = ["/v1"] )
class AccountController (
    private val accountService : IAccountService ,
    private val jwtTokenService : JwtTokenService
) {

    @CheckValidation
    @PostMapping( value = ["/sign-up"] )
    fun registerAccount(
        @Valid @RequestBody body : RegisterAccountDto,
        bindingResult : BindingResult
    ) : ResponseEntity<AppResponse> {
        try{
            accountService.createNewAccount(body)
            return ResponseEntity.ok()
                .body(
                    AppResponse(
                        responseDescription = "Successfully registered new account!",
                        status = HttpStatus.CREATED.value()
                    )
                )
        }catch(e : Exception){
            return ResponseEntity.badRequest()
                .body(
                    AppResponse(
                        responseDescription = StringUtil.formatErrorMessage(e.message),
                        status = HttpStatus.BAD_REQUEST.value()
                    )
                )
        }
    }

    @CheckValidation
    @PostMapping( value = ["/sign-in"] )
    fun signInAccount(
        @Valid @RequestBody dto : LoginRequestDto ,
        bindingResult: BindingResult
    ) : ResponseEntity<DataResponse> {
       val savedAccount = accountService.loginAccount(dto.email , dto.password)

       val payload = HashMap<String,String>()
        payload["username"] = savedAccount.username
        payload["email"] = savedAccount.email
        payload["accountId"] = savedAccount.id ?: ""

        val token = jwtTokenService.generate(
            payload,
            accountService.getTokenExpiredDate()
        )

        return ResponseEntity.ok(
            DataResponse(
                responseDescription = "Successfully logged in!",
                status = HttpStatus.OK.value(),
                data = LoginResponseDto(
                    username = savedAccount.username,
                    email = savedAccount.email,
                    followerCount = savedAccount.followerCount ?: 0,
                    followingCount = savedAccount.followingCount ?: 0,
                    lastLoggedInDate = savedAccount.lastLoggedInDate ?: LocalDateTime.now(),
                    level = savedAccount.level,
                    points = savedAccount.points,
                    token = token
                )
            )
        )
    }

}