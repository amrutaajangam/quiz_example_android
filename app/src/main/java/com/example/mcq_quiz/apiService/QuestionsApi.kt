package com.example.mcq_quiz.apiService

import com.example.mcq_quiz.model.Question
import retrofit2.http.GET

interface QuestionsApi {
    @GET("/avatar/questions")
    suspend fun getQuestions(): List<Question>
}