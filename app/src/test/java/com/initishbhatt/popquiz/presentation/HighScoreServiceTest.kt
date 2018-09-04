package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.data.repository.QuizDataDao
import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.presentation.highscore.HighScoreContract
import com.initishbhatt.popquiz.presentation.highscore.HighScoreService
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class HighScoreServiceTest : BaseTest() {

    private var userDataDao: UserDataDao = mock()
    private lateinit var service: HighScoreContract.Service
    private var quizDataDao:QuizDataDao = mock()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        service = HighScoreService(userDataDao,quizDataDao)
    }

    @Test
    fun `test user data fetch`() {
        //given
        val observer = TestObserver.create<Any>()
        whenever(userDataDao.getAllUsers()).thenReturn(Single.just(listOf(TestDataFactory.mockUserEntity)))

        //when
        service.getUsersWithScores().subscribe(observer)

        //then
        verify(userDataDao).getAllUsers()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }
}