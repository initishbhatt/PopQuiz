package com.initishbhatt.popquiz.di

import android.app.Application
import com.initishbhatt.popquiz.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author nitishbhatt
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    internal abstract fun bindActivity(): MainActivity
}