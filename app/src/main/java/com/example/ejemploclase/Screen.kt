package com.example.ejemploclase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

sealed class Screen(val title: String,val route: String){
    object Login:Screen("Login","login_screen")
    object Discover:Screen("Discover", "discover_screen")
    object Workout:Screen("Workout", "workout_screen")
    object Aaa:Screen("Discover", "discover_screen")

}
