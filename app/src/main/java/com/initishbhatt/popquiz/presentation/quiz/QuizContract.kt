package com.initishbhatt.popquiz.presentation.quiz

import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author nitishbhatt
 */
interface QuizContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun displayQuestions(quizDataEntity: QuizDataEntity)
        fun updateTimer(time: Long)
        fun updateScore()
        fun checkAnswerAndUpdate(text: String)
        fun updateScoreCorrectAnswer()
        fun updateScoreWrongAnswer()
        fun openHighScoreFragment()
    }

    interface Presenter {
        fun setView(view: View)
        fun removeView()
        fun fetchQuestions()
        fun showQuestions()
        fun onOptionClick(view: android.view.View)
        fun verify(text: String, answer: Int, id: Int)
        fun updateUserScore(score: Int?)
    }

    interface Service {
        fun fetchQuestionsFromServer(): Completable
        fun getQuizQuestions(): Single<List<QuizDataEntity>>
        fun verifyAnswer(text: String, answer: Int, id: Int): Single<Boolean>
        fun updateUserScore(score:Int?): Completable
    }
}
