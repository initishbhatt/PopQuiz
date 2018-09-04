package com.initishbhatt.popquiz.presentation.highscore

import com.initishbhatt.popquiz.data.repository.UserDataEntity
import io.reactivex.Completable
import io.reactivex.Single

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
        fun showScores()
    }

    interface Service {
        fun getUsersWithScores(): Single<List<UserDataEntity>>
        fun clearQuestions(): Completable
    }
}