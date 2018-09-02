package com.initishbhatt.popquiz.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.databinding.FragmentHighScoreBinding
import com.initishbhatt.popquiz.presentation.highscore.HighScoreContract
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class HighScoreFragment : DaggerFragment(), HighScoreContract.View {
    @Inject
    lateinit var highScorePresenter: HighScoreContract.Presenter
    private lateinit var binding: FragmentHighScoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_high_score, container, false)
        return binding.root
    }
}