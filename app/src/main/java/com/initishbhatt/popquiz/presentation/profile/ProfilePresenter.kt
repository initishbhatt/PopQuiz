package com.initishbhatt.popquiz.presentation.profile

import com.initishbhatt.popquiz.data.repository.toUserEntity
import com.initishbhatt.popquiz.util.SchedulerProvider
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfilePresenter @Inject constructor(
        private val service: ProfileContract.Service,
        private val schedulerProvider: SchedulerProvider) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    override fun setView(view: ProfileContract.View) {
        this.view = view
    }

    override fun onPlayClick(model: ProfileBindingModel) {
        val userId = UUID.randomUUID().toString()

        fun onSuccess() {
            service.setUserId(userId)
            view?.openQuizFragment()
        }

        fun onError(error: Throwable) {
            Timber.e(error)
        }
       service.storeUserData(model.toUserEntity(userId))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::onSuccess, ::onError)
    }

    override fun onHowToPlayClick() {
        view?.openHowToPlayDialog()
    }

    override fun onHighScoreClick() {
        view?.openHighScoreFragment()
    }
}
