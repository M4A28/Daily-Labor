package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGreen
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsYellow


@Composable
fun StatusBanner(
    status: String,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor, statusText) = when (status) {
        "ACTIVE" -> Triple(
            BsGreen,
            White,
            stringResource(R.string.valid)
        )
        "EXPIRED" -> Triple(
            BsRed,
            White,
            stringResource(R.string.expired)
        )
        "PENDING" -> Triple(
            BsYellow,
            White,
            stringResource(R.string.about_to_expire)
        )

        else -> {
            Triple(
                Color(0xFF9E9E9E).copy(alpha = 0.1f),
                Color(0xFF212121),
                stringResource(R.string.suspended)
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statusText,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
