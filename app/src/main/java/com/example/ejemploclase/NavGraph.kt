package com.example.ejemploclase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejemploclase.ui.theme.EjemploClaseTheme

@Composable
fun SetupGraph(

    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LogInScreen()
        }
        //PARA PONER OTRA SCREEN VER VIDEO EN RESOURCES DE NAVIGATION BASICS MINUTO 9:30
//        composable(
//            route = Screen.Login.route
//        ){
//            LogInScreen()
//        }
    }
}