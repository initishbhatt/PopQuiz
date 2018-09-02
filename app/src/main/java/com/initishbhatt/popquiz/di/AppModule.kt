package com.initishbhatt.popquiz.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.initishbhatt.popquiz.PopQuizApp
import com.initishbhatt.popquiz.data.repository.PopQuizDatabase
import com.initishbhatt.popquiz.data.repository.QuizDataDao
import com.initishbhatt.popquiz.data.repository.UserDataDao
import com.initishbhatt.popquiz.data.store.PrefStore
import com.initishbhatt.popquiz.presentation.profile.ProfileContract
import com.initishbhatt.popquiz.presentation.profile.ProfileService
import com.initishbhatt.popquiz.util.SchedulerProvider
import com.initishbhatt.popquiz.util.SchedulersProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author nitishbhatt
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: PopQuizApp): Context = application.applicationContext

    @Provides
    fun provideSchedulers(providerImpl: SchedulersProviderImpl): SchedulerProvider = providerImpl

    @Provides
    @Singleton
    fun provideProfileService(userDataDao: UserDataDao,
                              store: PrefStore
    ): ProfileContract.Service {
        return ProfileService(userDataDao, store)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): PopQuizDatabase {
        return Room
                .databaseBuilder(context, PopQuizDatabase::class.java, "quizdatabase.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideUserDataDao(db: PopQuizDatabase): UserDataDao {
        return db.userDataDao()
    }

    @Singleton
    @Provides
    fun provideQuizDataDao(db: PopQuizDatabase): QuizDataDao {
        return db.quizDataDao()
    }

    @Provides
    @Singleton
    fun provideStore(sharedPreferences: SharedPreferences): PrefStore {
        return PrefStore(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("popQuiz", Context.MODE_PRIVATE)
    }
}