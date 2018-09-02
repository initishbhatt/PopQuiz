package com.initishbhatt.popquiz.di

import com.initishbhatt.popquiz.data.QuizApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author nitishbhatt
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideQuizService(retrofit: Retrofit): QuizApi {
        return retrofit.create(QuizApi::class.java)
    }
}