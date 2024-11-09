package com.mohammed.mosa.eg.dailylabor.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = TajawaleFont),
    displayMedium = baseline.displayMedium.copy(fontFamily = TajawaleFont),
    displaySmall = baseline.displaySmall.copy(fontFamily = TajawaleFont),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = TajawaleFont),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = TajawaleFont),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = TajawaleFont),
    titleLarge = baseline.titleLarge.copy(fontFamily = TajawaleFont),
    titleMedium = baseline.titleMedium.copy(fontFamily = TajawaleFont),
    titleSmall = baseline.titleSmall.copy(fontFamily = TajawaleFont),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = TajawaleFont),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = TajawaleFont),
    bodySmall = baseline.bodySmall.copy(fontFamily = TajawaleFont),
    labelLarge = baseline.labelLarge.copy(fontFamily = TajawaleFont),
    labelMedium = baseline.labelMedium.copy(fontFamily = TajawaleFont),
    labelSmall = baseline.labelSmall.copy(fontFamily = TajawaleFont),
)
