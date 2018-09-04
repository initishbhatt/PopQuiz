package com.initishbhatt.popquiz.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.data.repository.QuizDataEntity
import com.initishbhatt.popquiz.databinding.FragmentQuizBinding
import com.initishbhatt.popquiz.presentation.quiz.QuizContract
import com.initishbhatt.popquiz.util.replaceFragment
import com.initishbhatt.popquiz.view.binding.QuizBindingModel
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
    private var correctAnswer: Int = 0
    private var questionId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizPresenter.setView(this)
        binding.model = model
        binding.presenter = quizPresenter
        quizPresenter.fetchQuestions()
    }

    override fun updateScore() {
        quizPresenter.updateUserScore(binding.model?.score)
    }

    override fun openHighScoreFragment() {
        replaceFragment(HighScoreFragment(),R.id.fragment)
    }

    override fun updateTimer(time: Long) {
        binding.model?.timer = time.toInt()
    }

    override fun showLoading() {
        binding.model?.questionsNotAvailable = true
    }

    override fun hideLoading() {
        binding.model?.questionsNotAvailable = false
    }

    override fun displayQuestions(quizDataEntity: QuizDataEntity) {
        quizDataEntity
                .apply {
                    binding.model?.question = question
                    binding.model?.optionOne = optionOne
                    binding.model?.optionTwo = optionTwo
                    binding.model?.optionThree = optionThree
                    binding.model?.optionFour = optionFour
                    correctAnswer = answer
                    questionId = id
                }
    }

    override fun checkAnswerAndUpdate(text: String) {
        quizPresenter.verify(text, correctAnswer, questionId)
    }

    override fun updateScoreCorrectAnswer() {
        binding.model?.score = (binding.model?.score!!.plus(20).plus(10.minus(binding.model?.timer!!)))
        quizPresenter.showQuestions()
    }

    override fun updateScoreWrongAnswer() {
        binding.model?.score = binding.model?.score!!.minus(10)
        quizPresenter.showQuestions()
    }

    override fun onDestroy() {
        super.onDestroy()
        quizPresenter.removeView()
    }
}
