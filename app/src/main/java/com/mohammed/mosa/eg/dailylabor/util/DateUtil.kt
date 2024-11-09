package com.mohammed.mosa.eg.dailylabor.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Date): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}


fun formatDateWithMonthName(date: Date): String {
    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
}

fun calculateDaysUntilExpiry(expiryDate: Date): Int {
    return ((expiryDate.time - Date().time) / (1000 * 60 * 60 * 24)).toInt()
}

fun Double.formatCurrency(value: Double = this): String {
    return NumberFormat.getCurrencyInstance().format(value)
}




// Extension function to format dates nicely
fun Date.toFormattedString(pattern: String = "yyyy-MM-dd"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this)
}