package com.example.r02095187_homework_who_wants_to_be_a_millionaire

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val rewardAmount: Int
)
