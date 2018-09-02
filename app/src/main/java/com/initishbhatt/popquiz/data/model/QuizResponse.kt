package com.initishbhatt.popquiz.data.model

/**
 * @author nitishbhatt
 */

data class QuizResponse(
        val quizes: List<Quiz>
)

data class Quiz(
        val question: String,
        val options: List<String>,
        val answer: Int
)