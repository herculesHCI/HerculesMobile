package com.example.ejemploclase

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppBar(navController: NavHostController, function: @Composable () -> Unit)
{
    Scaffold(
            topBar = {
                TopAppBar(
                        backgroundColor = Color.LightGray,
                        title = { Text( text = "Hercules") },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                        imageVector = Icons.Default.Settings ,
                                        contentDescription = null,
                                ) //Ver Como verga mover hacia la derecha
                            }
                        }
                )
             },
            bottomBar = {
                val selectedIndex = remember { mutableStateOf(0) }
                BottomNavigation(elevation = 10.dp ,
                        backgroundColor = Color.LightGray) {

                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Home,"")
                    },
                            label = { Text(text = "Discover") },
                            selected = (selectedIndex.value == 0),
                            onClick = {
                                selectedIndex.value = 0;
                                navController.navigate("discover");
                            })

                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.PlayArrow,"")
                    },
                            label = { Text(text = "Workout") },
                            selected = (selectedIndex.value == 1),
                            onClick = {
                                selectedIndex.value = 1;
                                navController.navigate("workout");
                            })

                    BottomNavigationItem(icon = {
                        Icon(imageVector = Icons.Default.Favorite,"")
                    },
                            label = { Text(text = "Favorite") },
                            selected = (selectedIndex.value == 2),
                            onClick = {
                                selectedIndex.value = 2;
                                navController.navigate("favorite");
                            })
                }
            }


    ) {
        function()
    }
}








