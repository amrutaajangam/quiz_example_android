package com.example.mcq_quiz.apiService

import com.example.mcq_quiz.model.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val api: QuestionsApi) {
     fun getQuestions(): Flow<List<Question>> = flow {
       emit(api.getQuestions())
    }
}