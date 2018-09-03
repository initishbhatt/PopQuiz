package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScorePresenter @Inject constructor(
        userDataDao: UserDataDao,
        schedulerProvider: SchedulerProvider
) : HighScoreContract.Presenter {

    init {
        userDataDao.getAllUsers()
                .map { it }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success, Timber::e)
    }

    private var view: HighScoreContract.View? = null

    override fun setView(view: HighScoreContract.View) {
        this.view = view
    }

    private fun success(user: List<UserDataEntity>) {
        val highScorers = user.sortedBy { it.userScore }
                .take(5)
        view?.showHighScores(highScorers)
    }

    override fun removeView() {
        view = null
    }
}