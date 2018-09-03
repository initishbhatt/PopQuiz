package com.initishbhatt.popquiz.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import com.initishbhatt.popquiz.data.model.Quiz
import io.reactivex.Single

/**
 * @author nitishbhatt
 */
@Entity(tableName = "QuizQuestions")
data class QuizDataEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var question: String,
        var optionOne: String,
        var optionTwo: String,
        var optionThree: String,
        var optionFour: String,
        var answer: Int
)


@Dao
interface QuizDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeQuizQuestions(quizDataEntity: QuizDataEntity)

    @Query("SELECT * FROM QuizQuestions")
    fun getQuizQuestions(): Single<List<QuizDataEntity>>

    @Query("SELECT * FROM QuizQuestions where id is :id")
    fun getSelectedQuestion(id: Int): Single<QuizDataEntity>
}

fun Quiz.toQuizEntity(): QuizDataEntity = QuizDataEntity(
        question = question,
        answer = answer,
        optionOne = options[0],
        optionTwo = options[1],
        optionThree = options[2],
        optionFour = options[3])