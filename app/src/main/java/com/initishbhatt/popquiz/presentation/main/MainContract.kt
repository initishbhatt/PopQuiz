package com.initishbhatt.popquiz.presentation.main

/**
 * @author nitishbhatt
 */
interface MainContract {
    interface View

    interface Presenter {
        fun setView(view: View)
        fun removeView()
    }
}