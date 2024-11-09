package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGreen
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsOrange
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsYellow
import com.mohammed.mosa.eg.dailylabor.util.calculateDaysUntilExpiry
import com.mohammed.mosa.eg.dailylabor.util.formatDateWithMonthName

import java.util.Date

@Composable
fun ExpiryDateRow(
    label: String,
    date: Date
) {
    val daysUntilExpiry = calculateDaysUntilExpiry(date)
    val (backgroundColor, textColor) = when {
        daysUntilExpiry <=  0  -> BsRed.copy(alpha = 0.1f) to BsRed
        daysUntilExpiry in  1..15  -> BsOrange.copy(alpha = 0.1f) to BsOrange
        daysUntilExpiry < 30 -> BsYellow.copy(alpha = 0.1f) to Color(0xFFB86E00)
        else -> BsGreen.copy(alpha = 0.1f) to BsGreen

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BsPrimaryDark
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = formatDateWithMonthName(date),
                style = MaterialTheme.typography.bodyMedium,
                color = BsPrimaryDark
            )
            Text(
                text = if(daysUntilExpiry <= 0) stringResource(R.string.expired) else stringResource(R.string.days_remaining, daysUntilExpiry),
                style = MaterialTheme.typography.bodySmall,
                color = textColor
            )
        }
    }
}
