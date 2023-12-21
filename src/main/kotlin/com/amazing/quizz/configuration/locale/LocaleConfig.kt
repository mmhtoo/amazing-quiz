package com.amazing.quizz.configuration.locale

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@Configuration
class LocaleConfig {

    @Bean
    fun localeResolver() : AcceptHeaderLocaleResolver {
        val resolver = AcceptHeaderLocaleResolver()
        resolver.setDefaultLocale(Locale("en"))
        return resolver
    }

    @Bean
    fun messageSource() : MessageSource {
        val messageSource : ResourceBundleMessageSource = ResourceBundleMessageSource()
        messageSource.setBasename("i18n/messages")
        messageSource.setDefaultLocale(Locale("en"))
        messageSource.setDefaultEncoding("ISO-8859-1")
        return messageSource
    }

}