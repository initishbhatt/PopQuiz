package com.initishbhatt.popquiz.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import io.reactivex.Single

/**
 * @author nitishbhatt
 */
@Entity(tableName = "QuizQuestions")
data class QuizDataEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 1,
        var question: String,
        // var options: List<String>,
        var answer: String
)


@Dao
interface QuizDataDao {
    @Insert
    fun storeQuizQuestions(quizDataEntity: QuizDataEntity)

    @Query("SELECT * FROM QuizQuestions")
    fun getQuizQuestions(): Single<List<QuizDataEntity>>
}