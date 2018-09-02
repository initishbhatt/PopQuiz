package com.initishbhatt.popquiz.presentation.profile

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.data.store.PrefStore
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfileService @Inject constructor(
        var userDataDao: UserDataDao,
        var prefStore: PrefStore
) : ProfileContract.Service {
    override fun storeUserData(userData: UserDataEntity): Completable =
            Completable.fromCallable {
                userDataDao.insertUserData(userData)
            }

    override fun setUserId(userId: String) =
    prefStore.setUserId(userId)

}