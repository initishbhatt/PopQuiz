package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScorePresenter @Inject constructor(
        var service: HighScoreContract.Service,
        var schedulerProvider: SchedulerProvider
) : HighScoreContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    private var view: HighScoreContract.View? = null

    override fun setView(view: HighScoreContract.View) {
        this.view = view
    }

    override fun showScores() {
        service.getUsersWithScores()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::success, Timber::e)
                .addToDisposable()
    }

    private fun success(user: List<UserDataEntity>) {
        val highScorers = user.sortedBy { it.userScore }
                .take(5)
        view?.showHighScores(highScorers)
    }

    override fun removeView() {
        view = null
        compositeDisposable.clear()
    }

    private fun Disposable.addToDisposable() = apply {
        compositeDisposable.add(this)
    }
}