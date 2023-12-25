package com.amazing.quizz.controller

import com.amazing.quizz.annotation.CheckValidation
import com.amazing.quizz.dto.request.RegisterAccountDto
import com.amazing.quizz.dto.response.AppResponse
import com.amazing.quizz.service.IAccountService
import com.amazing.quizz.utility.StringUtil
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( value = ["/v1"] )
class AccountController (
    val accountService : IAccountService
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

}