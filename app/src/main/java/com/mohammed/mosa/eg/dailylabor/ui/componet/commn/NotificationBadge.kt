package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsWarning


@Composable
fun NotificationBadge(
    onClick: () -> Unit,
    notifySize: Int
) {
    Box(modifier = Modifier.padding(12.dp)) {
        IconButton(onClick = { onClick() }) {
            Icon(

                painter = painterResource(id = R.drawable.bell),
                contentDescription = "NotificationIcon"
            )
        }
        if(notifySize > 0){

            Badge(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(if(notifySize < 10) BsWarning else BsRed)
            ) {
                Text(text = if(notifySize > 50) "50+" else  "$notifySize")
            }
        }
    }
}