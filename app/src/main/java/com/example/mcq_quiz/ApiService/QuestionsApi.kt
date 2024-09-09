package com.example.mcq_quiz.ApiService

import com.example.mcq_quiz.Model.Question
import retrofit2.http.GET

interface QuestionsApi {
    @GET("/avatar/questions")
    suspend fun getQuestions() : List<Question>
}