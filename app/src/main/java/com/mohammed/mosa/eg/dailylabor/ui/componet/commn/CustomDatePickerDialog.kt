package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryLight
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary
import com.mohammed.mosa.eg.dailylabor.ui.theme.OffWhite

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConform: (Long) -> Unit,
    state: DatePickerState
) {

    DatePickerDialog(
        modifier = modifier.padding(end = 32.dp, start = 32.dp),
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        colors = DatePickerDefaults.colors(
            containerColor = OffWhite,
            titleContentColor = BsPrimaryDark,
            headlineContentColor = BsPrimaryDark,
            selectedDayContainerColor = BsSecondary,
            selectedYearContainerColor = OffWhite,
            selectedDayContentColor = BsPrimary,
        ),
        confirmButton = {
            Button(onClick = {
                onConform(state.selectedDateMillis ?: Date().time)
                onDismiss()
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BsPrimary,
                    contentColor = Color.White
                ),) {
                Text(stringResource(R.string.ok),)
            }
        },
        dismissButton = {
            TextButton(onClick = {
               onDismiss()
            }) {
                Text(stringResource(R.string.cancel), color = BsRed)
            }
        }
    ) {
        DatePicker(
            state = state,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                titleContentColor = BsPrimary,
                headlineContentColor = BsPrimary,
                weekdayContentColor = BsPrimaryDark,
                subheadContentColor = BsPrimaryLight,
                yearContentColor = BsPrimary,
                currentYearContentColor = BsSecondary,
                selectedYearContainerColor = BsPrimary,
                selectedYearContentColor = OffWhite,
                dayContentColor = BsPrimary,
                selectedDayContainerColor = BsPrimary,
                selectedDayContentColor = OffWhite,
                todayContentColor = BsSecondary,
                todayDateBorderColor = BsSecondary
            )
        )
    }
}
