package com.initishbhatt.popquiz.presentation.quiz

import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class QuizPresenter @Inject constructor() : QuizContract.Presenter {
    private var view: QuizContract.View? = null
    override fun setView(view: QuizContract.View) {
        this.view = view
    }
}