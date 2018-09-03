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
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class ProfileFragment : DaggerFragment(), ProfileContract.View {

    @Inject
    lateinit var profilePresenter: ProfileContract.Presenter
    private var model: ProfileBindingModel = ProfileBindingModel()
    private lateinit var binding: FragmentProflieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proflie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePresenter.setView(this@ProfileFragment)
        binding.model = model
        binding.presenter = profilePresenter
    }


    override fun openQuizFragment() {
        val manager = fragmentManager
        val transaction = manager?.beginTransaction()
        transaction?.replace(R.id.fragment, QuizFragment())
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
        transaction?.replace(R.id.fragment, HighScoreFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.removeView()
    }
}
