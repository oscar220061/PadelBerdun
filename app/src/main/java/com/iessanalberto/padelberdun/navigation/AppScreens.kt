package com.iessanalberto.padelberdun.navigation

sealed class AppScreens(val route: String){
    object LoginScreen: AppScreens (route = "login_screen")
    object ReservasScreen: AppScreens (route = "reservas_screen")
    object RegisterScreen: AppScreens (route = "register_screen")

}