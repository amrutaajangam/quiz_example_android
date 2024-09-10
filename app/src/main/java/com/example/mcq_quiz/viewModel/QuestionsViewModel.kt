package com.example.mcq_quiz.viewModel

import android.hardware.biometrics.BiometricManager.Strings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcq_quiz.R
import com.example.mcq_quiz.apiService.QuestionsApi
import com.example.mcq_quiz.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val apiService: QuestionsApi) : ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _answer = MutableStateFlow<String?>(null)
    val answer: StateFlow<String?> = _answer

    init {
        fetchQuestions()
    }


    private fun fetchQuestions() {
        viewModelScope.launch {
            flow {
                emit(apiService.getQuestions())
            }.catch { e ->
                emit(emptyList())
            }.collect { questions ->
                _questions.value = questions
            }
        }
    }

    fun checkAnswer(selectedAnswer: String, correctAnswer: String) {
        when (selectedAnswer) {
            correctAnswer -> _answer.value = R.string.correct_answer.toString()
            else -> _answer.value = R.string.wrong_answer.toString()
        }
    }

    fun resetAnswerResult() {
        _answer.value = null
    }
}