package com.amazing.quizz.service.implementation

import com.amazing.quizz.entitiy.QuizzCategoryEntity
import com.amazing.quizz.exception.custom.DuplicateEntityException
import com.amazing.quizz.repository.QuizzCategoryRepository
import com.amazing.quizz.service.IQuizzCategoryService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class QuizzCategoryService (
    private val quizzCategoryRepo : QuizzCategoryRepository
) : IQuizzCategoryService {

    @Cacheable( cacheNames = ["quizzCategories"] )
    override fun getAllQuizzCategories(): List<QuizzCategoryEntity> {
        return quizzCategoryRepo.findAll()
    }

    @CacheEvict( cacheNames = ["quizzCategories"] , allEntries = true)
    override fun createNewQuizzCategory(name: String): QuizzCategoryEntity {
        if(isQuizzNameDuplicate(name)){
            throw DuplicateEntityException("Duplicate quizz category!")
        }

        val newCategory = QuizzCategoryEntity(
            name = name,
            createdAt = LocalDateTime.now()
        )
        return quizzCategoryRepo.save(newCategory)
    }

    override fun getQuizzById(id: Int): QuizzCategoryEntity? {
        return quizzCategoryRepo.findById(id)
            .orElse(null)
    }

    override fun getQuizzByName(name: String): QuizzCategoryEntity? {
        return quizzCategoryRepo.findByName(name)
            .orElse(null)
    }

    override fun isQuizzNameDuplicate(name: String): Boolean {
        return getQuizzByName(name) != null
    }

}