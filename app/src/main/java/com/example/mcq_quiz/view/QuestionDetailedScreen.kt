package com.example.mcq_quiz.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mcq_quiz.model.Question
import com.example.mcq_quiz.viewModel.QuestionsViewModel

@Composable
fun QuestionDetailedScreen(viewModel: QuestionsViewModel, question: Question) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    val scaffoldState = rememberScaffoldState()
    val answer by viewModel.answer.collectAsState()

    LaunchedEffect(answer) {
        answer?.let {
            scaffoldState.snackbarHostState.showSnackbar(it)
            viewModel.resetAnswerResult()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "Question") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = question.question, style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(16.dp))

            question.possibleAnsers.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedAnswer = option
                            viewModel.checkAnswer(option, question.correctAnswer)
                        }
                        .padding(8.dp)
                ) {

                    RadioButton(
                        selected = selectedAnswer == option,
                        onClick = {
                            selectedAnswer = option
                            viewModel.checkAnswer(option, question.correctAnswer)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = option)
                }
            }
        }

    }
}