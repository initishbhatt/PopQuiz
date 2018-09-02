package com.initishbhatt.popquiz.di

import com.initishbhatt.popquiz.presentation.profile.ProfileContract
import com.initishbhatt.popquiz.presentation.profile.ProfilePresenter
import com.initishbhatt.popquiz.presentation.profile.ProfileService
import dagger.Binds
import dagger.Module

/**
 * @author nitishbhatt
 */
@Module
abstract class ProfileFragmentModule {
    @Binds
    @FragmentScope
    abstract fun provideProfilePresenter(presenter: ProfilePresenter): ProfileContract.Presenter
}