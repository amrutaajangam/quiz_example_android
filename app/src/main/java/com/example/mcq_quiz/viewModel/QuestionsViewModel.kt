package com.example.mcq_quiz.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcq_quiz.apiService.ApiHelperImpl
import com.example.mcq_quiz.apiService.QuestionsApi
import com.example.mcq_quiz.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val apiHelper: ApiHelperImpl) : ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _answer = MutableStateFlow<String?>(null)
    val answer: StateFlow<String?> = _answer

    init {
        fetchQuestions()
    }


    private fun fetchQuestions() {
        viewModelScope.launch {
            apiHelper.getQuestions()
                .flowOn(Dispatchers.IO)
                .catch {
                    emit(emptyList())
                }.collect { questions ->
                    _questions.value = questions
                }
        }
    }

    fun checkAnswer(selectedAnswer: String, correctAnswer: String) {
        if (selectedAnswer == correctAnswer)
            _answer.value = "Correct Answer!"
        else _answer.value = "Wrong Answer!"

    }

    fun resetAnswerResult() {
        _answer.value = null
    }
}