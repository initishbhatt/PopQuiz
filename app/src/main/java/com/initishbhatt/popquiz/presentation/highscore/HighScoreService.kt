package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScoreService @Inject constructor(
        private val userDataDao: UserDataDao
) : HighScoreContract.Service {

    override fun getUsersWithScores(): Single<List<UserDataEntity>> =
            userDataDao.getAllUsers()
                    .map { it }

}