package com.example.ejemploclase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Discover : BottomBarScreen(
        route = "DISCOVER",
        title = "Discover",
        icon = Icons.Default.Search
    )

    object Workout : BottomBarScreen(
        route = "WORKOUT",
        title = "Workout",
        icon = Icons.Default.PlayArrow
    )

    object Favorites : BottomBarScreen(
        route = "FAVORITES",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )
}