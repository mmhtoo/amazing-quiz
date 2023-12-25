package com.amazing.quizz.exception.handler

import com.amazing.quizz.dto.response.AppResponse
import com.amazing.quizz.exception.custom.DuplicateEntityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(
        UsernameNotFoundException::class,
        BadCredentialsException::class
    )
    fun handleUsernameAndBadCredentialsException() : ResponseEntity<AppResponse> {
        return ResponseEntity
            .badRequest()
            .body(
                AppResponse(
                    responseDescription =  "Invalid username or password!",
                    status = HttpStatus.BAD_REQUEST.value()
                )
            )
    }

    @ExceptionHandler(
        DuplicateEntityException::class
    )
    fun handleDuplicateEntityException(e : Exception) : ResponseEntity<AppResponse> {
        return ResponseEntity
            .badRequest()
            .body(
                AppResponse(
                    responseDescription = e.message ?: "Duplicate data!",
                    status = HttpStatus.BAD_REQUEST.value()
                )
            )
    }

}