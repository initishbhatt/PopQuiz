package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.presentation.highscore.HighScoreContract
import com.initishbhatt.popquiz.presentation.highscore.HighScorePresenter
import com.initishbhatt.popquiz.util.BaseTest
import com.initishbhatt.popquiz.util.RxSchedulersOverrideRule
import com.initishbhatt.popquiz.util.TestDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class HighScorePresenterTest : BaseTest() {
    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var presenter: HighScoreContract.Presenter
    private var view: HighScoreContract.View = mock()
    private var service: HighScoreContract.Service = mock()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = HighScorePresenter(service,schedulersProvider)
        presenter.setView(view)
    }

    @Test
    fun `test user fetch success`() {
        //given
        whenever(service.getUsersWithScores()).thenReturn(Single.just(listOf(TestDataFactory.mockUserEntity)))

        //when
        presenter.showScores()
        testScheduler.triggerActions()

        //then
        verify(view).showHighScores(listOf(TestDataFactory.mockUserEntity))
    }

    @Test
    fun `test user fetch failed`() {
        //given
        whenever(service.getUsersWithScores()).thenReturn(Single.error(Throwable()))

        //when
        presenter.showScores()
        testScheduler.triggerActions()

        //then
        verifyZeroInteractions(view)
    }

}