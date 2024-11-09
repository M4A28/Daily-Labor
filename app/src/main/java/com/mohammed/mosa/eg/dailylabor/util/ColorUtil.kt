package com.mohammed.mosa.eg.dailylabor.util

import androidx.compose.ui.graphics.Color

fun getColorFromString(colorName: String): Color {
    return when (colorName.lowercase()) {
        "red" -> Color(0xFFE53935)
        "blue" -> Color(0xFF1E88E5)
        "green" -> Color(0xFF43A047)
        "black" -> Color(0xFF212121)
        "white" -> Color(0xFFFAFAFA)
        "silver" -> Color(0xFFBDBDBD)
        "gray" -> Color(0xFF757575)
        "yellow" -> Color(0xFFFDD835)
        "orange" -> Color(0xFFFF9800)
        "purple" -> Color(0xFF8E24AA)
        "brown" -> Color(0xFF795548)
        else -> Color(0xFF9E9E9E)
    }
}
