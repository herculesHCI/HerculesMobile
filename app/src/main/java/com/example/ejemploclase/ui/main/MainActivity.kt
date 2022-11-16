package com.example.ejemploclase.ui.main

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
import com.example.ejemploclase.screens.DiscoverScreen
import com.example.ejemploclase.screens.FavoriteScreen
import com.example.ejemploclase.screens.PreviewScreen
import com.example.ejemploclase.screens.WorkoutScreen
import com.example.ejemploclase.data.network.util.getViewModelFactory


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
            if(!viewModel.uiState.isAuthenticated){
                viewModel.login("","")//TODO desharcodear
            }
            NavHost(navController = navController, startDestination = "discover" ){
                composable("discover"){
                    DiscoverScreen(navController)
                }
                composable("favorite"){
                    FavoriteScreen(navController)
                }
                composable("workout"){
                    WorkoutScreen(navController)
                }
                composable(route = "preview/{workoutId}",
                           arguments = listOf(navArgument("workoutId") {type = NavType.IntType} ) ){
                    navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("workoutId")
                    requireNotNull(id)
                    PreviewScreen(navController, id )
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){view,insets ->
            val bottom=insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom=bottom)
            insets
        }

    }
}
