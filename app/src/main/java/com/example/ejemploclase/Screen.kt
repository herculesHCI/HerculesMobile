package com.example.ejemploclase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

sealed class Screen(val title: String,val route: String){
    object Login:Screen("Login",route="login_screen")
    object Discover:Screen("Discover", "discover_screen")
}
