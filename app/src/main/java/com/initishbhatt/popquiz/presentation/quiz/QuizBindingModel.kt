package com.initishbhatt.popquiz.presentation.quiz

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.initishbhatt.popquiz.BR

/**
 * @author nitishbhatt
 */
class QuizBindingModel : BaseObservable() {
    @get:Bindable
    var questionsNotAvailable: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.questionsNotAvailable)
        }
    @get:Bindable
    var score: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.score)
        }

    @get:Bindable
    var timer: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.timer)
        }

    @get:Bindable
    var question: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.question)
        }

    @get:Bindable
    var optionOne: String = "option1"
        set(value) {
            field = value
            notifyPropertyChanged(BR.optionOne)
        }

    @get:Bindable
    var optionTwo: String = "option2"
        set(value) {
            field = value
            notifyPropertyChanged(BR.optionTwo)
        }

    @get:Bindable
    var optionThree: String = "option3"
        set(value) {
            field = value
            notifyPropertyChanged(BR.optionThree)
        }

    @get:Bindable
    var optionFour: String = "option4"
        set(value) {
            field = value
            notifyPropertyChanged(BR.optionFour)
        }
}