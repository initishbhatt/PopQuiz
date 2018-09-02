package com.initishbhatt.popquiz.di

import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.presentation.main.MainContract
import com.initishbhatt.popquiz.presentation.main.MainPresenter
import com.initishbhatt.popquiz.view.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author nitishbhatt
 */
@Module
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun presenter(presenter: MainPresenter): MainContract.Presenter

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    @FragmentScope
    internal abstract fun ProfileFragment(): ProfileFragment
}