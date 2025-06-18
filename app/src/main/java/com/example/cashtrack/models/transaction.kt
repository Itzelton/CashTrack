package com.example.cashtrack.models

data class Transaction(
    val description: String,
    val amount: Double,
    val category: String,
    val timestamp: Long
)