package com.initishbhatt.popquiz.util

import com.initishbhatt.popquiz.data.model.Quiz
import com.initishbhatt.popquiz.data.model.QuizResponse
import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.view.binding.ProfileBindingModel
import java.util.UUID

/**
 * @author nitishbhatt
 */
object TestDataFactory {
    val mockUserEntity = UserDataEntity(
            userGender = "male",
            userAge = "26",
            userName = "anyone",
            userId = UUID.randomUUID().toString(),
            userScore = 100
    )
    val model = ProfileBindingModel().apply {
        name = "any"
        age = "2"
        gender = "M"
    }

    val mockQuizEntity = QuizDataEntity(
            question = "any",
            optionFour = "4",
            optionOne = "1",
            optionThree = "3",
            optionTwo = "2",
            answer = 1
    )


    private val mockQuiz = Quiz(
            question = "any",
            answer = 1,
            options = listOf("1", "2", "3", "4")

    )
    val quizResponse = QuizResponse(
            quizes = listOf(mockQuiz)
    )

}

fun ProfileBindingModel.toUserEntity(userId: String) = UserDataEntity(userId = userId,
        userName = name,
        userAge = age,
        userGender = gender)