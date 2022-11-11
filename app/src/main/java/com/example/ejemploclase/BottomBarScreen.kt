package com.example.ejemploclase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "DISCOVER",
        title = "DISCOVER",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "WORKOUT",
        title = "WORKOUT",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "FAVORITES",
        title = "FAVORITES",
        icon = Icons.Default.Settings
    )
}