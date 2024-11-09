package com.mohammed.mosa.eg.dailylabor.ui.componet.commn.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.presentr.navigation.Route


sealed class NavigationItem(val route: String,
                            @StringRes val title: Int,
                            @DrawableRes val icon: Int
) {

    // fix this later
    object Home : NavigationItem(
        "Route.HomeScreen.route",
        R.string.home,
        R.drawable.home
    )

    object Labor : NavigationItem(
        Route.LaborScreen.route,
        R.string.labor,
        R.drawable.person
    )

    object Payment : NavigationItem(
       Route.PaymentScreen.route,
        R.string.payments,
        R.drawable.dollar_sign,
    )

    object Setting : NavigationItem(
        Route.SettingScreen.route,
        R.string.settings,
        R.drawable.settings,
    )

}