package com.example.mcq_quiz.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mcq_quiz.model.Question
import com.example.mcq_quiz.viewModel.QuestionsViewModel

@Composable
fun QuestionsListScreen(viewModel: QuestionsViewModel, onQuestionClick: (Question) -> Unit) {

    val questions by viewModel.questions.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "Questions List") })
        }
    ) {
        Column(Modifier.padding(it)) {
            LazyColumn {
                items(questions) { question ->
                    QuestionItem(question = question, onClick = { onQuestionClick(question) })
                }
            }
        }
    }

}

@Composable
fun QuestionItem(question: Question, onClick: () -> Unit) {
    Text(
        text = question.question,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    )
}