package com.amazing.quizz.controller

import com.amazing.quizz.annotation.CheckValidation
import com.amazing.quizz.dto.request.AppConfigUpdateDto
import com.amazing.quizz.dto.response.AppResponse
import com.amazing.quizz.dto.response.DataResponse
import com.amazing.quizz.entitiy.AppConfigEntity
import com.amazing.quizz.service.IAppConfigService
import com.amazing.quizz.utility.StringUtil
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping( value = ["/v1"] )
class AppConfigController (
    val appConfigService : IAppConfigService
) {

    @CheckValidation
    @PutMapping( value = ["/configurations/{id}"] )
    fun updateAppConfiguration(
        @Valid @RequestBody newConfig : AppConfigUpdateDto,
        bindingResult : BindingResult,
        @PathVariable( value = "id" ) id : Int,
    ) : ResponseEntity<AppResponse> {
        try{
            if(bindingResult.hasErrors()){
                bindingResult
                    .fieldErrors
                    .forEach {
                        println(it.field)
                        println(it.defaultMessage)
                    }
                return ResponseEntity.badRequest()
                    .body(
                        AppResponse("Validation failed!",HttpStatus.BAD_REQUEST.value())
                    )
            }
            appConfigService.updateConfig(
                AppConfigEntity(
                    id = id,
                    rewardPointPerQuizz = newConfig.rewardPointPerQuizz,
                    rewardExpPerQuizz = newConfig.rewardExpPerQuizz,
                    requiredPointPerQuizz = newConfig.requiredPointPerQuizz
                )
            )
            return ResponseEntity.ok(
             AppResponse(
                 responseDescription = "Successfully Updated!",
                 status = HttpStatus.OK.value())
            )
        }catch(e : Exception){
            return ResponseEntity.badRequest()
                .body(AppResponse(
                    responseDescription = StringUtil.formatErrorMessage(e.message),
                    status = HttpStatus.BAD_REQUEST.value()
                ))
        }
    }

    @GetMapping( value = ["/configurations"] )
    fun getAppConfiguration() : ResponseEntity<DataResponse> {
        val config = appConfigService.getConfig()
        return ResponseEntity.ok(
            DataResponse(
                responseDescription = "Success!",
                status = HttpStatus.OK.value(),
                data = if(config != null) object {
                    val requiredPointPerQuizz =  config.requiredPointPerQuizz
                    val rewardExpPerQuizz = config.rewardExpPerQuizz
                    val rewardPointPerQuizz = config.rewardPointPerQuizz
                } else null
            )
        )
    }

}