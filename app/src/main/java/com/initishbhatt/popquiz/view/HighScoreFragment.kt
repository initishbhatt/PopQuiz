package com.initishbhatt.popquiz.view

import android.databinding.DataBindingUtil
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import com.initishbhatt.popquiz.databinding.FragmentHighScoreBinding
import com.initishbhatt.popquiz.presentation.highscore.HighScoreContract
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration



/**
 * @author nitishbhatt
 */
class HighScoreFragment : DaggerFragment(), HighScoreContract.View {
    @Inject
    lateinit var highScorePresenter: HighScoreContract.Presenter
    override fun showHighScores(users: List<UserDataEntity>) {
        binding.highScoreRv.adapter = HighScoreItemAdapter(users)
    }

    private lateinit var binding: FragmentHighScoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_high_score, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        highScorePresenter.setView(this)
        binding.highScoreRv.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        highScorePresenter.showScores()
    }

    override fun onDestroy() {
        super.onDestroy()
        highScorePresenter.removeView()
    }
}