package com.initishbhatt.popquiz.presentation.quiz

import android.view.View
import android.widget.Button
import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
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
        private val quizService: QuizService,
        private val schedulerProvider: SchedulerProvider
) : QuizContract.Presenter {


    private var compositeDisposable = CompositeDisposable()
    private var count: Int = 0
    private var resumed: AtomicBoolean = AtomicBoolean()
    private var stopped: AtomicBoolean = AtomicBoolean()
    private var view: QuizContract.View? = null


    override fun setView(view: QuizContract.View) {
        this.view = view
    }

    override fun fetchQuestions() {
        view?.showLoading()
        quizService.fetchQuestionsFromServer()
                .subscribe()
    }

    override fun showQuestions() {
        compositeDisposable.clear()
        quizService.getQuizQuestions()
                .map { it }
                .subscribe(::success, ::error)
    }

    private fun success(quizData: List<QuizDataEntity>) {
        view?.apply {
            hideLoading()
            displayQuestions(quizData)
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

    override fun updateUserScore(score: Int?) {
        fun success() {
            view?.openHighScoreFragment()
        }
        quizService.updateUserScore(score)
                .subscribe(::success, Timber::e)
    }

    private fun error(error: Throwable) {
        view?.hideLoading()
        Timber.e(error)
    }


    override fun onOptionClick(view: View) {
        stopTimer()
        count = count.plus(1)
        this.view?.checkAnswerAndUpdated((view as Button).text.toString())
    }

    override fun verify(text: String, answer: Int, id: Int) {
        fun success(result: Boolean) {
            if (result) {
                view?.updateScoreCorrectAnswer()
            } else {
                view?.updateScoreWrongAnswer()
            }
        }

        quizService.verifyAnswer(text, answer, id)
                .subscribe(::success, Timber::e)
    }

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