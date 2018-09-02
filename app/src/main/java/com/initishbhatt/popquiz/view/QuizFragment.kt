package com.initishbhatt.popquiz.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.databinding.FragmentQuizBinding
import com.initishbhatt.popquiz.presentation.quiz.QuizBindingModel
import com.initishbhatt.popquiz.presentation.quiz.QuizContract
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author nitishbhatt
 */
class QuizFragment : DaggerFragment(), QuizContract.View {
    @Inject
    lateinit var quizPresenter: QuizContract.Presenter
    private var model: QuizBindingModel = QuizBindingModel()
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizPresenter.setView(this)
    }

}