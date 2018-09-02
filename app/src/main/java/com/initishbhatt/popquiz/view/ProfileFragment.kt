package com.initishbhatt.popquiz.view

import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.databinding.FragmentProflieBinding
import com.initishbhatt.popquiz.presentation.profile.ProfileBindingModel
import com.initishbhatt.popquiz.presentation.profile.ProfileContract
import com.initishbhatt.popquiz.presentation.profile.ProfileFormHandler
import com.initishbhatt.popquiz.util.hideSoftKeyboard
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfileFragment : DaggerFragment(), ProfileContract.View, Validator.ValidationListener {

    @Inject
    lateinit var profilePresenter: ProfileContract.Presenter
    private var model: ProfileBindingModel = ProfileBindingModel()
    private lateinit var binding: FragmentProflieBinding
    private lateinit var profileFormHandler: ProfileFormHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proflie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePresenter.setView(this)
        profileFormHandler = ProfileFormHandler(this)
        binding.model = model
        binding.handler = profileFormHandler
        binding.presenter = profilePresenter
    }


    override fun openQuizFragment() {
        val manager = fragmentManager
        val transaction = manager?.beginTransaction()
        transaction?.add(R.id.fragment, QuizFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    override fun openHowToPlayDialog() {
        val dialogLayout = AlertDialog.Builder(context)
        dialogLayout.setTitle(getString(R.string.how_to_play).toUpperCase())
        dialogLayout.setView(View.inflate(context, R.layout.dialog_how_to_play, null))
        dialogLayout.create().show()
    }

    override fun openHighScoreFragment() {
        val manager = fragmentManager
        val transaction = manager?.beginTransaction()
        transaction?.add(R.id.fragment, QuizFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        errors?.forEach {
            val view = it.view
            when {
                view.id == binding.userNameText.id -> {
                    val parentLayout = binding.userNameLabel
                    parentLayout.error = it.failedRules[0].getMessage(context)
                }
                view.id == binding.ageText.id -> {
                    val parentLayout = binding.ageLabel
                    parentLayout.error = it.failedRules[0].getMessage(context)
                }
                else -> {
                    val parentLayout = binding.genderLabel
                    parentLayout.error = it.failedRules[0].getMessage(context)
                }
            }
        }

    }

    override fun onValidationSucceeded() {
        binding.userNameText.clearFocus()
        binding.ageText.clearFocus()
        binding.genderText.clearFocus()
        activity?.hideSoftKeyboard()
        profilePresenter.onPlayClick(model)
    }


}
