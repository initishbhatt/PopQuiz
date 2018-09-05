package com.initishbhatt.popquiz.presentation.quiz

import com.initishbhatt.popquiz.data.QuizApi
import com.initishbhatt.popquiz.data.repository.QuizDataDao
import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.data.repository.toQuizEntity
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class QuizService @Inject constructor(
        private val quizApi: QuizApi,
        private val quizDataDao: QuizDataDao,
        private val userDataDao: UserDataDao
) : QuizContract.Service {

    override fun fetchQuestionsFromServer(): Completable =
            quizApi.fetchQuizQuestions()
                    .map { it.quizes }
                    .map {
                        it.forEach {
                            quizDataDao.storeQuizQuestions(it.toQuizEntity())
                        }
                    }.toCompletable()

    override fun getQuizQuestions(): Single<List<QuizDataEntity>> =
            quizDataDao.getQuizQuestions()

    override fun verifyAnswer(text: String, answer: Int, id: Int): Single<Boolean> {
        return quizDataDao.getSelectedQuestion(id)
                .map {
                    var result = false
                    when (answer) {
                        1 -> if (text == it.optionOne) result = true
                        2 -> if (text == it.optionTwo) result = true
                        3 -> if (text == it.optionThree) result = true
                        4 -> if (text == it.optionFour) result = true
                        else -> result = false
                    }
                    result
                }
    }

    override fun updateUserScore(score: Int?): Completable =
            Completable.fromCallable {
                fun success(userDataEntity: UserDataEntity) {
                    userDataDao.updateUserScore(score!!, userDataEntity.userId)
                }
                userDataDao.getAllUsers().map { it.last() }.subscribe(::success, Timber::e)
            }

}
