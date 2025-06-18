package com.example.cashtrack.utils

object CurrencyUtils {
    fun formatCurrency(amount: Double): String {
        return "GH₵${String.format("%.2f", amount)}"
    }

    fun formatCurrencyWithCommas(amount: Double): String {
        return "GH₵${String.format("%,.2f", amount)}"
    }
}