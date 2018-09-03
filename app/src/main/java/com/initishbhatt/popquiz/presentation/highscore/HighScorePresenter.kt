package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScorePresenter @Inject constructor(
        service: HighScoreContract.Service
) : HighScoreContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    init {
        service.getUsers()
                .subscribe(::success, Timber::e)
                .addToDisposable()
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
        compositeDisposable.clear()
    }

    private fun Disposable.addToDisposable() = apply {
        compositeDisposable.add(this)
    }
}