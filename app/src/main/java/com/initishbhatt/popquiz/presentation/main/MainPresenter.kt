package com.initishbhatt.popquiz.presentation.main

import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class MainPresenter @Inject constructor() : MainContract.Presenter {
    private var view: MainContract.View? = null
    override fun setView(view: MainContract.View) {
        this.view = view
    }
}