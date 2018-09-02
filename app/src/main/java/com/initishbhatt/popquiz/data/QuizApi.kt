package com.initishbhatt.popquiz.data

import com.initishbhatt.popquiz.data.model.QuizResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author nitishbhatt
 */
interface QuizApi {
    @GET(".")
    fun fetchQuizQuestions(): Single<QuizResponse>
}