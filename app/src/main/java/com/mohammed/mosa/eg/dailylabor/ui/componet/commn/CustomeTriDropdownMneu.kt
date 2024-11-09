package com.mohammed.mosa.eg.dailylabor.ui.componet.commn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGray100
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGray800


@Composable
fun CustomTriDropDownMenu(
    actionOne: String,
    actionTwo: String,
    actionThree: String,
    onActionOne: () -> Unit = {},
    onActionTwo: () -> Unit = {},
    onActionThree: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }


    Box{
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
            )
        }

        DropdownMenu(
            modifier = Modifier.background(BsGray100),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(actionOne, color = BsGray800) },

                onClick = {
                    onActionOne()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(actionTwo, color = BsGray800) },
                onClick = {
                    onActionTwo()
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = { Text(actionThree, color = BsGray800) },
                onClick = {
                    onActionThree()
                    expanded = false
                }
            )
        }
    }
}



@Composable
fun CustomDropDownMenu(
    actionOne: String,
    actionTwo: String,
    onActionOne: () -> Unit = {},
    onActionTwo: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }


    Box(

    ){
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
            )
        }

        DropdownMenu(
            modifier = Modifier.background(BsGray100),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(actionOne, color = BsGray800) },

                onClick = {
                    onActionOne()
                }
            )
            DropdownMenuItem(
                text = { Text(actionTwo, color = BsGray800) },
                onClick = {
                    onActionTwo()
                }
            )
        }
    }
}




