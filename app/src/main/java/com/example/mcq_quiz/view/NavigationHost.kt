package com.example.mcq_quiz.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mcq_quiz.viewModel.QuestionsViewModel

@Composable
fun Navigation(viewModel: QuestionsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "questions") {
        composable("questions") {
            QuestionsListScreen(viewModel = viewModel) { question ->
                navController.navigate("${question.id}")
            }
        }
        composable(
            "{questionId}",
            arguments = listOf(navArgument("questionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val questionId = backStackEntry.arguments?.getInt("questionId") ?: return@composable
            val question = viewModel.questions.value.find { it.id == questionId }
            question?.let {
                QuestionDetailedScreen(viewModel = viewModel, question = it)
            }
        }
    }
}
