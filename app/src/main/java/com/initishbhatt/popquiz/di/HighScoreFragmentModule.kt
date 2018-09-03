package com.initishbhatt.popquiz.di

import com.initishbhatt.popquiz.presentation.highscore.HighScoreContract
import com.initishbhatt.popquiz.presentation.highscore.HighScorePresenter
import com.initishbhatt.popquiz.presentation.highscore.HighScoreService
import dagger.Binds
import dagger.Module

/**
 * @author nitishbhatt
 */
@Module
abstract class HighScoreFragmentModule {
    @Binds
    @FragmentScope
    abstract fun provideHighScorePresenter(presenter: HighScorePresenter): HighScoreContract.Presenter

    @Binds
    @FragmentScope
    abstract fun provideHighScoreService(service: HighScoreService): HighScoreContract.Service
}