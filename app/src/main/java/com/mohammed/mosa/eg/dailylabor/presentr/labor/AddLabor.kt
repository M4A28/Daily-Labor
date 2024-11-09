package com.mohammed.mosa.eg.dailylabor.presentr.labor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.CustomTextField
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.FormSection
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimary
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsRed
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary
import com.mohammed.mosa.eg.dailylabor.ui.theme.OffWhite


@Composable
fun AddLabor(
    onDismiss: () -> Unit,
    onSave: (Labor) -> Unit
){

    val context = LocalContext.current
    var laborName by remember { mutableStateOf("") }
    var laborIdNumber by remember { mutableStateOf("") }
    var laborPhone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(OffWhite)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            stringResource(R.string.labor_information),
            style = MaterialTheme.typography.headlineMedium,
            color = BsPrimaryDark,
            fontWeight = FontWeight.Bold
        )

        CustomTextField(
            value = laborName,
            onValueChange = { laborName = it },
            label = stringResource(R.string.labor_name),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.person),
                    contentDescription = null,
                    tint = BsSecondary
                )
            }
        )

        CustomTextField(
            value = laborIdNumber,
            onValueChange = { laborIdNumber = it },
            label = stringResource(R.string.labor_id_number),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.id_card),
                    contentDescription = null,
                    tint = BsSecondary
                )
            }
        )

        CustomTextField(
            value = laborPhone,
            onValueChange = { laborPhone = it },
            label = stringResource(R.string.labor_phone),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.phone),
                    contentDescription = null,
                    tint = BsSecondary
                )
            }
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OutlinedButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = White,
                    contentColor = BsRed
                )
            ) {
                Text(stringResource(R.string.cancel))
            }


            Button(
                onClick = { onSave(
                    Labor(
                        name = laborName.trim(),
                        idNumber = laborIdNumber.trim(),
                        phoneNumber = laborPhone.trim())

                )
                    laborName = ""
                    laborIdNumber = ""
                    laborPhone = ""
                          },
                colors = ButtonDefaults.buttonColors(containerColor = BsPrimary)
            ) {
                Text(stringResource(R.string.save))
            }


        }

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLaborDialog(
    onDismiss: () -> Unit,
    onSave: (Labor) -> Unit
){
    Dialog(

        onDismissRequest = { onDismiss() },
        content = {
            AddLabor(onDismiss = onDismiss, onSave = onSave)
        }
    )
}

