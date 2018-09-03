package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataEntity

/**
 * @author nitishbhatt
 */
interface HighScoreContract {
    interface View {
        fun showHighScores(users: List<UserDataEntity>)
    }

    interface Presenter {
        fun setView(view: View)
        fun removeView()
    }

    interface Service
}