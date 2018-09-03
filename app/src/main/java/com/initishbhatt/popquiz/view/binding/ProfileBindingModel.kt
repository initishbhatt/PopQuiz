package com.initishbhatt.popquiz.view.binding

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.initishbhatt.popquiz.BR

/**
 * @author nitishbhatt
 */
class ProfileBindingModel : BaseObservable() {
    @get:Bindable
    var name: String = ""
        set(username) {
            field = username
            notifyPropertyChanged(BR.name)
            isPlayerDetailAvailable = isFieldsSet

        }
    @get:Bindable
    var age: String = ""
        set(userage) {
            field = userage
            notifyPropertyChanged(BR.age)
            isPlayerDetailAvailable = isFieldsSet
        }
    @get:Bindable
    var gender: String = ""
        set(usergender) {
            field = usergender
            notifyPropertyChanged(BR.gender)
            isPlayerDetailAvailable = isFieldsSet
        }
    @get:Bindable
    var isPlayerDetailAvailable: Boolean = false
        set(detailsAvailable) {
            field = detailsAvailable
            notifyPropertyChanged(BR.playerDetailAvailable)
        }

    private val isFieldsSet: Boolean
        get() = !name.isEmpty() && !gender.isEmpty() && age.isNotEmpty()
}
