package com.mohammed.mosa.eg.dailylabor.presentr.labor

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.CustomTriDropDownMenu
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.InfoRow
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsGray200
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDarken
import com.mohammed.mosa.eg.dailylabor.util.dailPhone


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LaborCard(
    labor: Labor,
    onDelete: (Labor) -> Unit = {},
    onEdit: (Labor) -> Unit = {},
    onPayClick: (Labor) -> Unit = {}
){

    val context = LocalContext.current
    var showExtended by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = { showExtended = !showExtended },
                onLongClick = {
                    Intent().dailPhone(labor.phoneNumber, context)
                }
            )
            .clip(RoundedCornerShape(16.dp))
            .background(White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.background(White)
        ){
            Row(
                Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = labor.name,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = BsPrimaryDarken)

                CustomTriDropDownMenu(
                    actionOne = "Pay Now",
                    actionTwo = "Edit",
                    actionThree = "Delete",
                    onActionOne = { onPayClick(labor) },
                    onActionTwo = { onEdit(labor) },
                    onActionThree = { onDelete(labor) },

                    )

            }
            HorizontalDivider(modifier = Modifier.padding(start = 16.dp, end = 8.dp).background(BsGray200))

            AnimatedVisibility(showExtended,
                modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 8.dp)) {
                Column {
                    InfoRow(
                        icon = R.drawable.id_card,
                        label = stringResource(R.string.residence_number),
                        value = labor.idNumber
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    InfoRow(
                        icon = R.drawable.phone,
                        label = stringResource(R.string.phone_number),
                        value = labor.phoneNumber
                    )
                }

            }
        }




    }

}


@Preview(showBackground = true)
@Composable
fun LaborItemPreview(){
    LaborCard(
        labor = Labor(
            id = 1,
            name = "John Doe",
            idNumber = "12345678912345678",
            phoneNumber = "0123456789")
    )
}