package com.example.mcq_quiz.model

data class Question(
    val id: Int,
    val question: String,
    val possibleAnsers: List<String>,
    val correctAnswer: String
)
