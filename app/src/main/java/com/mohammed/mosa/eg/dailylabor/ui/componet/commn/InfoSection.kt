package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary


@Composable
fun InfoSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BsPrimaryDark,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}


@Composable
fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BsPrimaryDark
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = BsSecondary
        )
    }
}
