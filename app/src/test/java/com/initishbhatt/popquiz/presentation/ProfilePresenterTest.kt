package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.presentation.profile.ProfileContract
import com.initishbhatt.popquiz.presentation.profile.ProfilePresenter
import com.initishbhatt.popquiz.util.BaseTest
import com.initishbhatt.popquiz.util.RxSchedulersOverrideRule
import com.initishbhatt.popquiz.util.TestDataFactory
import com.initishbhatt.popquiz.util.toUserEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
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
class ProfilePresenterTest : BaseTest() {
    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    private lateinit var presenter: ProfileContract.Presenter
    private var service: ProfileContract.Service = mock()
    private var view: ProfileContract.View = mock()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = ProfilePresenter(service, schedulersProvider)
        presenter.setView(view)
    }

    @Test
    fun `test how to_play dialog_opened`() {
        //given

        //when
        presenter.onHowToPlayClick()

        //then
        verify(view).openHowToPlayDialog()
    }

    @Test
    fun `test high score _opened`() {
        //given

        //when
        presenter.onHighScoreClick()

        //then
        verify(view).openHighScoreFragment()
    }

    @Test
    fun `test user data stored on play click`() {
        //given
        whenever(service.storeUserData(TestDataFactory.model.toUserEntity())).thenReturn(Completable.complete())
        //when
        presenter.onPlayClick(TestDataFactory.model)
        testScheduler.triggerActions()
        //then
        verify(view).openQuizFragment()
    }

    @Test
    fun `test user data not stored on play click`() {
        //given
        whenever(service.storeUserData(TestDataFactory.model.toUserEntity())).thenReturn(Completable.error(Throwable()))
        //when
        presenter.onPlayClick(TestDataFactory.model)
        testScheduler.triggerActions()
        //then
        verifyZeroInteractions(view)
    }
}
