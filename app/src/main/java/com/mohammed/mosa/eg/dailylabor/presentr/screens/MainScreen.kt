package com.mohammed.mosa.eg.dailylabor.presentr.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.presentr.navigation.Route
import com.mohammed.mosa.eg.dailylabor.presentr.labor.LaborCard
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.NotificationBadge
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.bottomnavigation.BottomNavigationBar
import com.mohammed.mosa.eg.dailylabor.ui.componet.commn.bottomnavigation.NavigationItem
import com.mohammed.mosa.eg.dailylabor.ui.theme.BsPrimaryDark
import com.mohammed.mosa.eg.dailylabor.util.navigateToTab
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  MainScreen(){

    val items = listOf(
        NavigationItem.Home, // 1
        NavigationItem.Labor, //2
        NavigationItem.Payment,// 3
        NavigationItem.Setting, // 4

    )


    val navController = rememberNavController()
    val backStackState by navController.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val context = LocalContext.current
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.LaborScreen.route -> 1
            Route.PaymentScreen.route -> 2
            Route.SettingScreen.route -> 3
            else ->  -1
        }

    }

    Scaffold(

        topBar = {
            if(selectedItem == 0){
                CenterAlignedTopAppBar(
                    modifier = Modifier,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BsPrimaryDark,
                        titleContentColor = White,
                        navigationIconContentColor = White,
                        actionIconContentColor = White
                    ),
                    title = {
                        Text(text = stringResource(id = R.string.app_name), fontWeight = FontWeight.Bold)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(painter = painterResource(id = R.drawable.menu), contentDescription = null)
                        }
                    },
                    actions = {

                    }
                )
            }
        },

        bottomBar = {
            if(selectedItem == 0 || selectedItem == 1 || selectedItem == 2 || selectedItem == 3 ){

                BottomNavigationBar(
                    items = items,
                    selectedItem = selectedItem,
                    onItemClick = {index ->
                        when(index){
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.LaborScreen.route)
                            2 -> navigateToTab(navController, Route.PaymentScreen.route)
                            3 -> navigateToTab(navController, Route.SettingScreen.route)
                        }
                    }
                )
            }
        }

    ) {
            innerPadding ->

        NavHost(modifier = Modifier
            .padding(top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(Route.HomeScreen.route) {
                LaborCard(
                    labor = Labor(
                        id = 1,
                        name = "John Doe",
                        idNumber = "12345678912345678",
                        phoneNumber = "0123456789")
                )
            }

            composable(Route.LaborScreen.route) {
                LaborScreen()
            }


        }

    }






}