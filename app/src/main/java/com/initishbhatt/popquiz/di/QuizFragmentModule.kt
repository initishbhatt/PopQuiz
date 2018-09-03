package com.initishbhatt.popquiz.di

import com.initishbhatt.popquiz.presentation.quiz.QuizContract
import com.initishbhatt.popquiz.presentation.quiz.QuizPresenter
import com.initishbhatt.popquiz.presentation.quiz.QuizService
import dagger.Binds
import dagger.Module

/**
 * @author nitishbhatt
 */
@Module
abstract class QuizFragmentModule {
    @Binds
    @FragmentScope
    abstract fun provideQuizPresenter(presenter: QuizPresenter): QuizContract.Presenter

    @Binds
    @FragmentScope
    abstract fun provideQuizService(service: QuizService): QuizContract.Service
}