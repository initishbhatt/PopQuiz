package com.initishbhatt.popquiz.data.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author nitishbhatt
 */
@Database(entities = [UserDataEntity::class,
    QuizDataEntity::class], version = 3)
abstract class PopQuizDatabase : RoomDatabase() {
    abstract fun userDataDao(): UserDataDao
    abstract fun quizDataDao(): QuizDataDao
}