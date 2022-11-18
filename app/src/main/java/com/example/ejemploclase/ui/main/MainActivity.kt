package com.example.ejemploclase.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ejemploclase.FilterScreen
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.screens.*


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
            NavHost(navController = navController, startDestination = "start") {
                composable( route = "start"){
                    if (viewModel.uiState.isAuthenticated) {
                        navController.navigate("discover")
                    }else{
                        navController.navigate("login")
                    }
                }
                composable( route = "login"){
                    LogInScreen(navController)
                }
                composable(route = "discover/{filterName}",
                    arguments = listOf(navArgument("filterName") {
                        type = NavType.StringType
                    })
                ) { navBackStackEntry ->
                    val name = navBackStackEntry.arguments?.getString("filterName","Highest Rated")
                    DiscoverScreen(navController, name)
                }
                composable(route = "discover") {
                    DiscoverScreen(navController, "Highest Rated")
                }
                composable("favorite") {
                    FavoriteScreen(navController)
                }
                composable(route = "workout/{workoutId}",
                    arguments = listOf(navArgument("workoutId") {
                        type = NavType.IntType
                    })) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("workoutId",0)
                    if (id != null) {
                        WorkoutScreen(navController,id)
                    }
                }
                composable(route = "workout") {
                    WorkoutScreen(navController,0)
                }
                composable(route ="filter"){
                    FilterScreen(navController)
                }
                composable(route = "preview/{workoutId}",
                    arguments = listOf(navArgument("workoutId") {
                        type = NavType.IntType
                    })) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("workoutId")
                    requireNotNull(id)
                    PreviewScreen(navController, id)
                }
                composable( route = "settings"){
                    SettingsScreen(navController)
                }

            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
                val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                view.updatePadding(bottom = bottom)
                insets
            }

            val data: Uri? = intent?.data
            if(data != null) {
                val parameters: List<String> = data.pathSegments
                val params = parameters[parameters.size -1]
                if(parameters.size == 2 && parameters[0] == "workout"){
                    navController.navigate("preview/${params}")
                }
            }
        }
    }
}
