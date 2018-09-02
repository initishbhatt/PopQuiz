package com.initishbhatt.popquiz.presentation.profile

import android.databinding.BaseObservable
import android.databinding.Bindable

/**
 * @author nitishbhatt
 */
class ProfileBindingModel : BaseObservable() {
    @get:Bindable
    var name: String = ""
        set(username) {
            field = username
            notifyChange()
            isPlayerDetailAvailable = isFieldsSet

        }
    @get:Bindable
    var age: String = ""
        set(userage) {
            field = userage
            notifyChange()
            isPlayerDetailAvailable = isFieldsSet
        }
    @get:Bindable
    var gender: String = ""
        set(usergender) {
            field = usergender
            notifyChange()
            isPlayerDetailAvailable = isFieldsSet
        }
    @get:Bindable
    var isPlayerDetailAvailable: Boolean = false
        set(detailsAvailable) {
            field = detailsAvailable
            notifyChange()
        }

    private val isFieldsSet: Boolean
        get() = !name.isEmpty() && !gender.isEmpty() && age.isNotEmpty()
}
