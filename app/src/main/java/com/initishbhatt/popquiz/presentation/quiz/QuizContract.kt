package com.initishbhatt.popquiz.presentation.quiz

/**
 * @author nitishbhatt
 */
interface QuizContract {
    interface View {
    }

    interface Presenter {
        fun setView(view: View)
    }

    interface Service {
    }
}
