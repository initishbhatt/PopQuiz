package com.initishbhatt.popquiz.presentation.profile

import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.view.binding.ProfileBindingModel
import io.reactivex.Completable

/**
 * @author nitishbhatt
 */
interface ProfileContract {
    interface View {
        fun openQuizFragment()
        fun openHowToPlayDialog()
        fun openHighScoreFragment()
    }

    interface Presenter {
        fun setView(view: View)
        fun removeView()
        fun onPlayClick(model: ProfileBindingModel)
        fun onHowToPlayClick()
        fun onHighScoreClick()
    }

    interface Service {
        fun storeUserData(userData: UserDataEntity): Completable
        fun setUserId(userId: String)
    }
}
