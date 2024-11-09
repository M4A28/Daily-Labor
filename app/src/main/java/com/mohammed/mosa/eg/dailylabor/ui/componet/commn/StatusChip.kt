package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGreen
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsOrange
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsYellow
import com.mohammed.mosa.eg.dailylabor.util.calculateDaysUntilExpiry
import com.mohammed.mosa.eg.dailylabor.util.formatDate

import java.util.Date


@Composable
fun StatusChip(daysUntilExpiry: Int) {
    Surface(
        color = when {
            daysUntilExpiry in 16..30 -> BsYellow
            daysUntilExpiry in 1..15 -> BsOrange
            daysUntilExpiry <= 0 -> BsRed
            else -> BsGreen
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = when {
                daysUntilExpiry <= 0 -> stringResource(R.string.expired)
                daysUntilExpiry < 30 -> stringResource(R.string.days, daysUntilExpiry)
                else -> stringResource(R.string.valid)
            },
            color = White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}



@Composable
fun StatItem(
    icon: Painter,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = BsPrimaryDark
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BsSecondary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = BsPrimary
        )
    }
}


@Composable
fun DateRow(label: String, date: Date) {
    val daysUntilExpiry = calculateDaysUntilExpiry (date)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BsPrimaryDark,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatDate(date),
                style = MaterialTheme.typography.bodyMedium,
                color = BsPrimaryDark,
            )
            StatusChip( daysUntilExpiry)
        }
    }
}

