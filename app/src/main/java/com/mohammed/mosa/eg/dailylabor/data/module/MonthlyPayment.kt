package com.mohammed.mosa.eg.dailylabor.data.module

data class MonthlyPayment(
    val laborId: Int,
    val laborName: String,
    val month: String,
    val totalPayment: Double
)
