package com.initishbhatt.popquiz.presentation.profile

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfileService @Inject constructor(
        private var userDataDao: UserDataDao
) : ProfileContract.Service {
    override fun storeUserData(userData: UserDataEntity): Completable =
            Completable.fromCallable {
                userDataDao.insertUserData(userData)
            }
}