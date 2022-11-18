package com.example.ejemploclase

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

var openDialog = false

@Composable
fun NavRail(navController: NavHostController) {
    NavigationRail(elevation = 10.dp ,
        backgroundColor = Color.LightGray) {
        NavigationRailItem(icon = {
            Icon(imageVector = Icons.Default.Explore,"")
        },
            label = { Text(text = stringResource(id = R.string.discover)) },
            selected = (navController?.currentDestination?.route == "discover"),
            onClick = {
                navController.navigate("discover");
            },selectedContentColor = MaterialTheme.colors.secondary,
            unselectedContentColor = Color.Black)

        NavigationRailItem(icon = {
            Icon(imageVector = Icons.Default.PlayCircle,"")
        },
            label = { Text(text = stringResource(id = R.string.workout)) },
            selected = (navController?.currentDestination?.route == "workout"),
            onClick = {
                navController.navigate("workout");
            },selectedContentColor = MaterialTheme.colors.secondary,
            unselectedContentColor = Color.Black)

        NavigationRailItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = stringResource(id = R.string.favourites)) },
            selected = (navController?.currentDestination?.route == "favorite"),
            onClick = {
                navController.navigate("favorite");
            },selectedContentColor = MaterialTheme.colors.secondary,
            unselectedContentColor = Color.Black)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppBarCompact(navController: NavHostController, function: @Composable () -> Unit)
{
    Scaffold(
            topBar = {
                TopAppBar(
                        backgroundColor = Color.LightGray,
                        title = { Text( text = "Hercules") },
                        actions = {
                            IconButton(onClick = {
                                navController.navigate("settings");
                            }) {
                                Icon(
                                        imageVector = Icons.Default.Settings ,
                                        contentDescription = null,
                                )
                            }
                        }
                )
             },
            bottomBar = {
                BottomNavigation(elevation = 10.dp ,
                        backgroundColor = Color.LightGray) {
                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Explore,"")
                    },
                            label = { Text(text = stringResource(id = R.string.discover)) },
                        selected = (navController?.currentDestination?.route == "discover"),
                            onClick = {
                                navController.navigate("discover");
                            },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Black)
                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.PlayCircle,"")
                    },
                            label = { Text(text = stringResource(id = R.string.workout)) },
                            selected = (navController?.currentDestination?.route == "workout"),
                            onClick = {
                                navController.navigate("workout")
                            },selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Black)

                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Favorite,"")
                    },
                            label = { Text(text = stringResource(R.string.favourites)) },
                        selected = (navController?.currentDestination?.route == "favorite"),
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Black,
                            onClick = {
                                navController.navigate("favorite");
                            })
                }
            },

    ) { innerPadding -> Box(modifier = Modifier.padding(innerPadding))
        function()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppBarExpanded(navController: NavHostController, function: @Composable () -> Unit)
{
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                title = { Text( text = "Hercules") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("settings");
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings ,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        ) {
        Row(){
            NavRail(navController = navController)
            function()
        }
    }
}
