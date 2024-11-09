package com.mohammed.mosa.eg.dailylabor.presentr.navigation

sealed class Route(val route: String){

    object HomeScreen: Route(route = "homeScreen")

    object LaborScreen: Route(route = "laborScreen")

    object PaymentScreen: Route(route = "paymentScreen")

    object ReportScreen: Route(route = "reportScreen")

    object AppNavigation: Route(route = "appNavigation")

    object NotificationScreen: Route(route = "notificationScreen")

    object SettingScreen: Route(route = "settingScreen")

    object AboutScreen: Route(route = "aboutScreen")


    object AppStartNavigation: Route(route = "appStartNavigation")

    object OnBoardingScreen: Route(route = "onBoardingScreen")

    object SplashScreen: Route(route = "splashScreen")

    object LoginScreen: Route(route = "loginScreen")

    object RegisterScreen: Route(route = "registerScreen")

    object ForgotPasswordScreen: Route(route = "forgotPasswordScreen")

    object ResetPasswordScreen: Route(route = "resetPasswordScreen")

    object AppNavigationScreen: Route(route = "appNavigationScreen")
}