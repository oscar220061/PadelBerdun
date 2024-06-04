package com.iessanalberto.padelberdun.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iessanalberto.padelberdun.screens.LoginScreen
import com.iessanalberto.padelberdun.screens.RegisterScreen
import com.iessanalberto.padelberdun.screens.ReservasScreen
import com.iessanalberto.padelberdun.viewmodels.LoginScreenViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route ){
        composable(route = AppScreens.LoginScreen.route){ LoginScreen(navController = navController, loginScreenViewModel = LoginScreenViewModel()) }
        composable(route = AppScreens.ReservasScreen.route){ ReservasScreen(navController = navController)}
        composable(route = AppScreens.RegisterScreen.route){ RegisterScreen(navController = navController)}

    }
}