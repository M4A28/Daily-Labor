package com.mohammed.mosa.eg.dailylabor.presentr.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.module.Payment
import com.mohammed.mosa.eg.dailylabor.domain.viewmodel.LaborViewModel
import com.mohammed.mosa.eg.dailylabor.domain.viewmodel.PaymentViewModel
import com.mohammed.mosa.eg.dailylabor.presentr.labor.AddLaborDialog
import com.mohammed.mosa.eg.dailylabor.presentr.labor.LaborCard
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.CustomDropDownMenu
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.DeleteAlertDialog
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.SearchTopAppBar
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsSecondary
import com.mohammed.mosa.eg.dailylabor.ui.theme.OffWhite


@Composable
fun LaborScreen(){
    val laborViewModel: LaborViewModel = hiltViewModel()
    val paymentViewModel: PaymentViewModel = hiltViewModel()
    val labors by laborViewModel.labors.collectAsState(initial = emptyList())

    LaborContent(
        labors = labors,
        onDelete = {
            laborViewModel.deleteLabor(it)
        },
        onEdit = {
            laborViewModel.upsertLabor(it)
        },
        onPayClick = { payment, labor ->
           /* laborViewModel.upsertLabor(it)*/
        },
        onAdd = {
            laborViewModel.upsertLabor(it)
        }
    )


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaborContent(
    labors: List<Labor>,
    onAdd: (Labor) -> Unit = {},
    onDelete: (Labor) -> Unit = {},
    onEdit: (Labor) -> Unit = {},
    onPayClick: (Payment, Labor) -> Unit


) {


    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val isScrolled =
        listState.firstVisibleItemScrollOffset > 0 || listState.firstVisibleItemIndex > 0
    var showAddDailog by remember { mutableStateOf(false) }
    var showEditBottomSheet by remember { mutableStateOf(false) }

    var currentLabor by remember { mutableStateOf<Labor?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var showSearch by remember { mutableStateOf(false) }

    val filteredLabor = labors.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.idNumber.contains(searchQuery, ignoreCase = true) ||
                it.phoneNumber.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        containerColor = OffWhite,
        topBar = {
            SearchTopAppBar(
                title = stringResource(R.string.labor),
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                isSearchActive = showSearch,
                onSearchClick = {
                    showSearch = true
                },
                onCloseSearch = { showSearch = false  },
                actions = {
                    CustomDropDownMenu(
                        actionOne = stringResource(R.string.export_to_pdf),
                        actionTwo = stringResource(R.string.export_txo_excel),
                        onActionOne = {  },
                        onActionTwo = {  }

                    )
                }
            )
        },

        floatingActionButton = {
            AnimatedVisibility(
                visible = !isScrolled ,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                FloatingActionButton(onClick = {
                    showAddDailog = true
                },
                    containerColor = BsSecondary){
                    Icon(painter = painterResource(id = R.drawable.add), contentDescription = "Add Labor", tint = White)
                }
            }
        },


    ) { innerPadding ->

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
        ) {
            items(filteredLabor) { labor ->
                LaborCard(
                    labor = labor,
                    onDelete = { showDeleteDialog = true
                        currentLabor = labor },
                    onEdit = { onEdit(labor) },
                    onPayClick = { /*onPayClick(labor)*/ }
                )
            }

        }

    }
    if(showDeleteDialog){
        DeleteAlertDialog(
            isVisible = showDeleteDialog,
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                onDelete(currentLabor!!)
                showDeleteDialog = false
            },
            itemName = currentLabor?.name + " " + currentLabor?.idNumber
        )
    }

    if (showAddDailog) {

            // Sheet content
            AddLaborDialog(
                onSave = {
                    onAdd(it)
                    showAddDailog = false
                },
                onDismiss = {
                    showAddDailog = false
                }
            )

    }


}