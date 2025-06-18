package com.example.cashtrack.models


data class SavingsGoal(
    val name: String,
    val targetAmount: Float,
    val currentAmount: Float
) {
    val progressPercentage: Int
        get() = ((currentAmount / targetAmount) * 100).toInt()
}