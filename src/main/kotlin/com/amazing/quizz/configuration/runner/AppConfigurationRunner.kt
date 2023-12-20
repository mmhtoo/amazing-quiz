package com.amazing.quizz.configuration.runner

import com.amazing.quizz.entitiy.AppConfigEntity
import com.amazing.quizz.service.IAppConfigService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AppConfigurationRunner(
    val appConfigService: IAppConfigService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
       val config = AppConfigEntity(
          requiredPointPerQuizz = 5,
           rewardExpPerQuizz = 10,
           rewardPointPerQuizz = 10,
       )
        appConfigService.createConfig(config)
    }

}