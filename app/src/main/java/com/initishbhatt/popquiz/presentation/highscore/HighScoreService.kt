package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScoreService @Inject constructor(
        private val userDataDao: UserDataDao,
        private val schedulerProvider: SchedulerProvider
) : HighScoreContract.Service {

    override fun getUsers(): Single<List<UserDataEntity>> =
            userDataDao.getAllUsers()
                    .map { it }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())

}