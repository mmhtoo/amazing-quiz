package com.amazing.quizz.service.implementation

import com.amazing.quizz.entitiy.AppConfigEntity
import com.amazing.quizz.repository.AppConfigRepository
import com.amazing.quizz.service.IAppConfigService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AppConfigService (
    val appConfigRepo : AppConfigRepository
) : IAppConfigService {


    override fun createConfig(config: AppConfigEntity): AppConfigEntity {
        val savedConfig  = appConfigRepo.findAll()

        // if saved configuration have, we will return already have config
        if(savedConfig.size == 1){
            return savedConfig[0]
        }
       val newConfig = AppConfigEntity(
           requiredPointPerQuizz = config.requiredPointPerQuizz,
           rewardExpPerQuizz = config.rewardExpPerQuizz,
           rewardPointPerQuizz = config.rewardPointPerQuizz,
           issuedAt = LocalDateTime.now()
       )
        return appConfigRepo.save(newConfig)
    }

    override fun updateConfig(config: AppConfigEntity): AppConfigEntity {
        if(config.id == null){
            throw Exception("Configuration Id is missing!")
        }

        val savedConfig = appConfigRepo.findById(config.id)
        if(savedConfig.isEmpty){
            throw Exception("Invalid configuration to update!")
        }

        val newConfig = AppConfigEntity(
            id =  savedConfig.get().id,
            rewardPointPerQuizz = config.rewardPointPerQuizz,
            rewardExpPerQuizz = config.rewardExpPerQuizz,
            requiredPointPerQuizz = config.requiredPointPerQuizz,
            updatedAt = LocalDateTime.now()
        )
        return appConfigRepo.save(newConfig)
    }

    override fun getConfig(): AppConfigEntity? {
        return appConfigRepo.findAll()[0]
    }

}