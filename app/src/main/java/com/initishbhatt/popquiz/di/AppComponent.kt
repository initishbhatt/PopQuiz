package com.initishbhatt.popquiz.di

import android.app.Application
import com.initishbhatt.popquiz.PopQuizApp
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author nitishbhatt
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class, NetworkModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class])

interface AppComponent : AndroidInjector<PopQuizApp> {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: PopQuizApp): Builder
    }
}