package com.initishbhatt.popquiz.presentation.quiz

import android.view.View
import android.widget.Button
import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import com.initishbhatt.popquiz.util.random
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class QuizPresenter @Inject constructor(
        private val quizService: QuizContract.Service,
        private val schedulerProvider: SchedulerProvider
) : QuizContract.Presenter {


    private var compositeDisposable = CompositeDisposable()
    private var count: Int = 0
    private var resumed: AtomicBoolean = AtomicBoolean()
    private var stopped: AtomicBoolean = AtomicBoolean()
    private var view: QuizContract.View? = null
    private val localQuizData = mutableListOf<QuizDataEntity>()


    override fun setView(view: QuizContract.View) {
        this.view = view
    }

    override fun fetchQuestions() {
        fun  success(){
            showQuestions()
        }
        view?.showLoading()
        quizService.fetchQuestionsFromServer()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success,Timber::e)
                .addToDisposable()
    }

    /**
     * Display new question for the quiz
     */
    override fun showQuestions() {
        compositeDisposable.clear()
        quizService.getQuizQuestions()
                .map { it }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success, ::error)
    }

    private fun success(quizData: List<QuizDataEntity>) {
        val question = removeRedundantQuestions(quizData)
        view?.apply {
            hideLoading()
            displayQuestions(question)
            localQuizData.add(question)
        }
        when {
            count < 5 -> startTimer()
            else -> {
                count = 0
                view?.updateScore()
                compositeDisposable.clear()
            }
        }
    }

    private fun removeRedundantQuestions(quizData: List<QuizDataEntity>): QuizDataEntity {
        val localQuiz = ArrayList<QuizDataEntity>(quizData)
        if (quizData.isNotEmpty()) {
            //remove all previous questions
            for (item in localQuizData) {
                localQuiz.remove(item)
            }
        }
        val random = (0..localQuiz.lastIndex).random()
        return localQuiz[random]
    }

    private fun error(error: Throwable) {
        view?.hideLoading()
        Timber.e(error)
    }

    /**
     * Update scorecard of user and show High Score View after 5 questions
     */
    override fun updateUserScore(score: Int?) {
        fun success() {
            view?.openHighScoreFragment()
        }
        quizService.updateUserScore(score)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success, Timber::e)
    }

    /**
     * action when user clicks on the given answer choices
     */
    override fun onOptionClick(view: View) {
        stopTimer()
        count = count.plus(1)
        this.view?.checkAnswerAndUpdated((view as Button).text.toString())
    }

    /**
     * verify the answer given by user
     */
    override fun verify(text: String, answer: Int, id: Int) {
        fun success(result: Boolean) {
            if (result) {
                view?.updateScoreCorrectAnswer()
            } else {
                view?.updateScoreWrongAnswer()
            }
        }

        quizService.verifyAnswer(text, answer, id)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success, Timber::e)
    }

    /**
     *Timer for each question
     */
    private fun startTimer() {

        fun success(time: Long) {
            if (time <= 10) view?.updateTimer(time)
        }
        resumed.set(true)
        stopped.set(false)
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(schedulerProvider.ui())
                .filter { resumed.get() }
                .take(11)
                .takeWhile { !stopped.get() }
                .map { it + 1 }
                .subscribe(::success, Timber::e)
                .addToDisposable()

    }

    fun pauseTimer() {
        resumed.set(false)
    }

    fun resumeTimer() {
        resumed.set(true)
    }

    private fun stopTimer() {
        stopped.set(true)
        compositeDisposable.clear()
    }

    private fun Disposable.addToDisposable() = apply {
        compositeDisposable.add(this)
    }

    override fun removeView() {
        this.view = null
        compositeDisposable.clear()
    }
}