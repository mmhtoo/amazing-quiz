package com.amazing.quizz.utility

class StringUtil {

    companion object {
        @JvmStatic
        fun formatErrorMessage( message : String? ): String {
            return message ?: "Something went wrong, please try again or later!"
        }
    }

}