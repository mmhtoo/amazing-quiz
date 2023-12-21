package com.amazing.quizz.service

import com.amazing.quizz.entitiy.AppConfigEntity

interface IAppConfigService {

    fun createConfig(config: AppConfigEntity) : AppConfigEntity

    fun updateConfig(config: AppConfigEntity) : AppConfigEntity

    fun getConfig() : AppConfigEntity?

}