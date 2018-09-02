package com.initishbhatt.popquiz.presentation.profile

import android.widget.EditText
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.databinding.FragmentProflieBinding
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Order

/**
 * @author nitishbhatt
 */

class ProfileFormHandler(listener: Validator.ValidationListener) {
    private val validator: Validator = Validator(this)
    private var binding: FragmentProflieBinding? = null

    @Order(1)
    @NotEmpty(messageResId = R.string.name_empty)
    private var nameEditText: EditText? = null

    @Order(2)
    @NotEmpty(messageResId = R.string.age_error)
    private var ageEditText: EditText? = null

    @Order(2)
    @NotEmpty(messageResId = R.string.gender_error)
    private var genderEditText: EditText? = null

    init {
        validator.setValidationListener(listener)
        validator.validationMode = Validator.Mode.BURST
    }

    internal fun bind(binding: FragmentProflieBinding) {
        setBinding(binding)
        binding.let {
            nameEditText = it.userNameText
            ageEditText = it.ageText
            genderEditText = it.genderText
        }
    }

    private fun setBinding(binding: FragmentProflieBinding) {
        this.binding = binding
    }

    fun validate() {
        clearErrors()
        validator.validate()
    }

    private fun clearErrors() {
        binding?.apply {
            userNameLabel.error = null
            ageLabel.error = null
            genderLabel.error = null
        }
    }

    fun onDestroy() = validator.setValidationListener(EmptyValidatorListener())

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        clearErrors()
    }
}

/**
 * Empty ValidationListener used to prevent memory leaks because we cannot set the ValidationListener to null.
 */
class EmptyValidatorListener : Validator.ValidationListener {
    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValidationSucceeded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}