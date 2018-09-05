package com.initishbhatt.popquiz.presentation.profile

import com.initishbhatt.popquiz.data.repository.toUserEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import com.initishbhatt.popquiz.view.binding.ProfileBindingModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfilePresenter @Inject constructor(
        private val service: ProfileContract.Service,
        private val schedulerProvider: SchedulerProvider) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    private var compositeDisposable = CompositeDisposable()
    override fun setView(view: ProfileContract.View) {
        this.view = view
    }

    override fun onPlayClick(model: ProfileBindingModel) {

        fun onSuccess() {
            view?.openQuizFragment()
        }

        fun onError(error: Throwable) {
            Timber.e(error)
        }
        service.storeUserData(model.toUserEntity())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::onSuccess, ::onError)
                .addToDisposable()
    }

    override fun onHowToPlayClick() {
        view?.openHowToPlayDialog()
    }

    override fun onHighScoreClick() {
        view?.openHighScoreFragment()
    }

    override fun removeView() {
        view = null
        compositeDisposable.clear()
    }

    private fun Disposable.addToDisposable() = apply {
        compositeDisposable.add(this)
    }
}