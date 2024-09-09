package com.example.mcq_quiz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcq_quiz.ApiService.QuestionsApi
import com.example.mcq_quiz.Model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val apiService: QuestionsApi) : ViewModel() {
    private val _questions = MutableLiveData<List<Question>>()
    val questions : LiveData<List<Question>> = _questions

    init {
        viewModelScope.launch {
            try {
                _questions.value =apiService.getQuestions()
            }
            catch (e:Exception)
            {

            }
        }
    }
}