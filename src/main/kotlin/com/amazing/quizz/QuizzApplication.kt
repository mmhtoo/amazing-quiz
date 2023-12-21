package com.amazing.quizz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy
class QuizzApplication

fun main(args: Array<String>) {
	runApplication<QuizzApplication>(*args)
}
