package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.presentation.quiz.QuizContract
import com.initishbhatt.popquiz.presentation.quiz.QuizPresenter
import com.initishbhatt.popquiz.util.BaseTest
import com.initishbhatt.popquiz.util.RxSchedulersOverrideRule
import com.initishbhatt.popquiz.util.TestDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import timber.log.Timber
import java.util.concurrent.TimeUnit
import io.reactivex.plugins.RxJavaPlugins



/**
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class QuizPresenterTest : BaseTest() {
    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private var service: QuizContract.Service = mock()
    private var view: QuizContract.View = mock()
    private lateinit var presenter: QuizContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = QuizPresenter(service, schedulersProvider)
        presenter.setView(view)
    }

    @Test
    fun `test questions fetch success`() {
        //given
        whenever(service.fetchQuestionsFromServer()).thenReturn(Completable.complete())

        //when
        presenter.fetchQuestions()
        testScheduler.triggerActions()

        //then
        verify(view).showLoading()
    }

    @Test
    fun `test questions are shown`() {
        //given
        whenever(service.getQuizQuestions()).thenReturn(Single.just(listOf(TestDataFactory.mockQuizEntity)))
        RxJavaPlugins.setComputationSchedulerHandler { ignore -> testScheduler }
        //when
        presenter.showQuestions()
        testScheduler.triggerActions()

        //then
        verify(view).hideLoading()
        verify(view).displayQuestions(TestDataFactory.mockQuizEntity)
    }

    @Test
    fun `questions not shown`() {
        //given
        whenever(service.getQuizQuestions()).thenReturn(Single.error(Throwable()))

        //when
        presenter.showQuestions()
        testScheduler.triggerActions()

        //then
        verify(view).hideLoading()
    }

    @Test
    fun `score card is updated`() {
        //given
        whenever(service.updateUserScore(1)).thenReturn(Completable.complete())
        //when
        presenter.updateUserScore(1)
        testScheduler.triggerActions()
        //then
        verify(view).openHighScoreFragment()
    }

    @Test
    fun `score card is not updated`() {
        //given
        whenever(service.updateUserScore(1)).thenReturn(Completable.error(Throwable()))
        //when
        presenter.updateUserScore(1)
        testScheduler.triggerActions()
        //then
        verifyZeroInteractions(view)
    }

    @Test
    fun `verify answer is correct`() {
        //given
        whenever(service.verifyAnswer("answer", 1, 1)).thenReturn(Single.just(true))
        //when
        presenter.verify("answer", 1, 1)
        testScheduler.triggerActions()
        //then
        verify(view).updateScoreCorrectAnswer()

    }

    @Test
    fun `verify answer is wrong`() {
        //given
        whenever(service.verifyAnswer("answer", 1, 1)).thenReturn(Single.just(false))
        //when
        presenter.verify("answer", 1, 1)
        testScheduler.triggerActions()
        //then
        verify(view).updateScoreWrongAnswer()
    }

}