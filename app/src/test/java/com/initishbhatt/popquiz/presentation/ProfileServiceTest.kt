package com.initishbhatt.popquiz.presentation

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.presentation.profile.ProfileContract
import com.initishbhatt.popquiz.presentation.profile.ProfileService
import com.initishbhatt.popquiz.util.BaseTest
import com.initishbhatt.popquiz.util.TestDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author nitishbhatt
 */
@RunWith(MockitoJUnitRunner::class)
class ProfileServiceTest : BaseTest() {
    private var userDataDao: UserDataDao = mock()
    private lateinit var service: ProfileContract.Service

    @Before
    fun setup() {
        service = ProfileService(userDataDao)
    }


    @Test
    fun `test user data stored`() {
        //given
        val observer = TestObserver.create<Any>()
        //when
        service.storeUserData(TestDataFactory.mockUserEntity).subscribe(observer)
        //then
        verify(userDataDao).insertUserData(TestDataFactory.mockUserEntity)
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
    }
}