package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGreen
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsOrange
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryLight
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsYellow


@Composable
fun WarningCard(
    title: String,
    subtitle: String,
    daysUntilExpiry: Int,
    modifier: Modifier = Modifier
) {
    val warningColor = when {
        daysUntilExpiry < 0 -> BsRed
        daysUntilExpiry < 15 -> BsOrange
        daysUntilExpiry < 30 -> BsYellow // yellow
        else -> BsGreen // green
    }

    val warningText = when {
        daysUntilExpiry < 0 -> stringResource(R.string.expired)
        daysUntilExpiry == 0 -> stringResource(R.string.expires_today)
        daysUntilExpiry == 1 -> stringResource(R.string.expires_tomorrow)
        else -> stringResource(R.string.expires_in_days, daysUntilExpiry)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),

        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.alert_triangle),
                contentDescription = "Warning",
                tint = warningColor,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = BsPrimaryDark,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BsPrimary
                )
                Text(
                    text = warningText,
                    style = MaterialTheme.typography.bodySmall,
                    color = BsPrimaryLight,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




@Preview
@Composable
fun WarningCardPreview() {

        WarningCard(
            title = "Product Name",
            subtitle = "Product Description",
            daysUntilExpiry = 5
        )


}
