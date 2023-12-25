package com.amazing.quizz.service

import com.amazing.quizz.entitiy.QuizzCategoryEntity

interface IQuizzCategoryService {

    fun getAllQuizzCategories() : List<QuizzCategoryEntity>

    fun createNewQuizzCategory(name : String) : QuizzCategoryEntity

    fun getQuizzById(id : Int) : QuizzCategoryEntity?

    fun getQuizzByName(name : String) : QuizzCategoryEntity?

    fun isQuizzNameDuplicate(name: String) : Boolean

}