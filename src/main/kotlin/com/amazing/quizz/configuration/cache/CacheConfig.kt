package com.amazing.quizz.configuration.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Configuration
import java.util.Collections

@Configuration
@EnableCaching
class CacheConfig {

    fun cacheManager() : CacheManager {
        return ConcurrentMapCacheManager(
            "quizzCategories",
            "quizzs"
        )
    }

}