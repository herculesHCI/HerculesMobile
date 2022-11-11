package com.example.ejemploclase.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejemploclase.BottomBarScreen
import com.example.ejemploclase.DiscoverScreen
import com.example.ejemploclase.FavoriteScreen
import com.example.ejemploclase.ScreenContent

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Discover.route
    ) {
        composable(route = BottomBarScreen.Discover.route) {
            DiscoverScreen()
        }
        composable(route = BottomBarScreen.Workout.route) {
            ScreenContent(
                name = BottomBarScreen.Workout.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoriteScreen()
        }
    }
}
