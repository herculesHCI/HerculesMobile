package com.example.ejemploclase

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel

var openDialog = false

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
                            IconButton(onClick = {
                                navController.navigate("settings");
                            }) {
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











