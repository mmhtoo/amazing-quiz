package com.amazing.quizz.aspect

import com.amazing.quizz.dto.response.AppResponse
import com.amazing.quizz.dto.response.ErrorResponse
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.BindingResultUtils
import org.springframework.validation.ValidationUtils
import kotlin.reflect.typeOf

@Aspect
@Component
class CheckValidationAspect {

    @Around( value = "@annotation(com.amazing.quizz.annotation.CheckValidation) && args(..)" )
    fun doCheckValidation(pjp : ProceedingJoinPoint) : Any {
       val binding = pjp.args[1]
           ?: return ResponseEntity
               .internalServerError()
               .body(
                   AppResponse(
                       "Something went wrong, please try again or later!",
                       HttpStatus.INTERNAL_SERVER_ERROR.value()
                   )
               )
        if(binding !is BindingResult){
             return ResponseEntity
                .internalServerError()
                .body(
                    AppResponse(
                        "Something went wrong, please try again or later!",
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
                )
        }

        if(binding.hasErrors()){
            val errors = HashMap<String,String?>()
            print(binding.fieldErrors)

            binding.fieldErrors
                .forEach {
                    print(it.defaultMessage)
                    errors[it.field] = it.defaultMessage
                }
            return ResponseEntity.badRequest()
                .body(
                    ErrorResponse(
                        responseDescription = "Invalid request!",
                        status = HttpStatus.BAD_REQUEST.value(),
                        errors = errors
                    )
                )
        }

        return pjp.proceed()
    }

}