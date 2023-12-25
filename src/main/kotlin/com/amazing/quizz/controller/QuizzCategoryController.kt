package com.amazing.quizz.controller

import com.amazing.quizz.annotation.CheckValidation
import com.amazing.quizz.dto.request.QuizzCategoryCreateDto
import com.amazing.quizz.dto.response.DataResponse
import com.amazing.quizz.dto.response.QuizzCategoryResponse
import com.amazing.quizz.service.implementation.QuizzCategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( value = ["/v1"] )
class QuizzCategoryController (
    private val quizzCategoryService : QuizzCategoryService
) {

    @GetMapping( value = ["/quizz-categories"] )
    fun getAllQuizzCategories() : ResponseEntity<DataResponse> {
        return ResponseEntity.ok()
            .body(
                DataResponse(
                    responseDescription = "Success!",
                    status = HttpStatus.OK.value(),
                    data = quizzCategoryService.getAllQuizzCategories()
                )
            )
    }

    @CheckValidation
    @PostMapping( value = ["/quizz-categories"] )
    fun createQuizzCategory(
        @Valid @RequestBody dto : QuizzCategoryCreateDto,
        bindingResult : BindingResult
    ) : ResponseEntity<DataResponse> {
        val savedCategoryQuizz = quizzCategoryService.createNewQuizzCategory(dto.name)
        return ResponseEntity.ok(
            DataResponse(
                responseDescription = "Successfully created new category!",
                status = HttpStatus.CREATED.value(),
                data = QuizzCategoryResponse(
                    name = savedCategoryQuizz.name,
                    id = savedCategoryQuizz.id ?: 0
                )
            )
        )
    }

}