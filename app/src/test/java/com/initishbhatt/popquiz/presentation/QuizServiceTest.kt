package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.data.QuizApi
import com.initishbhatt.popquiz.data.repository.QuizDataDao
import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.store.PrefStore
import com.initishbhatt.popquiz.presentation.quiz.QuizContract
import com.initishbhatt.popquiz.presentation.quiz.QuizService
import com.initishbhatt.popquiz.util.BaseTest
import com.initishbhatt.popquiz.util.TestDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class QuizServiceTest : BaseTest() {
    private var quizApi: QuizApi = mock()
    private var quizDataDao: QuizDataDao = mock()
    private var userDataDao: UserDataDao = mock()
    private var prefStore: PrefStore = mock()
    private lateinit var service: QuizContract.Service

    @Before
    fun setup() {
        service = QuizService(quizApi, quizDataDao, userDataDao, prefStore)
    }

    @Test
    fun `test user scores updated`() {
        //given
        whenever(prefStore.getCurrentUserId()).thenReturn("id")
        val observer = TestObserver.create<Any>()
        //when
        service.updateUserScore(1).subscribe(observer)

        //then
        verify(userDataDao).updateUserScore(1, prefStore.getCurrentUserId())
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }

    @Test
    fun `quiz questions fetched from server`() {
        //given
        val observer = TestObserver.create<Any>()
        whenever(quizApi.fetchQuizQuestions()).thenReturn(Single.just(TestDataFactory.quizResponse))
        //when
        service.fetchQuestionsFromServer().subscribe(observer)
        //then
        verify(quizApi).fetchQuizQuestions()
        verify(quizDataDao).storeQuizQuestions(TestDataFactory.mockQuizEntity)
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }

    @Test
    fun `test quiz questions available in dao`() {
        //given
        val observer = TestObserver.create<Any>()
        whenever(quizDataDao.getQuizQuestions()).thenReturn(Single.just(listOf(TestDataFactory.mockQuizEntity)))
        //when
        service.getQuizQuestions().subscribe(observer)
        //then
        verify(quizDataDao).getQuizQuestions()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }

    @Test
    fun `test verification`() {
        //given
        val observer = TestObserver.create<Any>()
        whenever(quizDataDao.getSelectedQuestion(1)).thenReturn(Single.just(TestDataFactory.mockQuizEntity))
        //when
        service.verifyAnswer("1", 1, 1).subscribe(observer)
        //then
        verify(quizDataDao).getSelectedQuestion(1)
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }

}