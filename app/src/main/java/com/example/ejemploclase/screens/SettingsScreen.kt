package com.example.ejemploclase.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemploclase.AppBarCompact
import com.example.ejemploclase.R
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingsScreen(navController: NavHostController, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())) {
    AppBarCompact(navController = navController) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 25.dp)
            .verticalScroll(rememberScrollState())){
            Column {
                Row(){
                    Column(){
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(vertical = 10.dp)
                            )
                        }
                        Text(text= stringResource(R.string.settings_title),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Row(){
                    Text(stringResource(R.string.settings_lang))
                }
                var expanded = remember { mutableStateOf(false) }
                var currentLanguage: MutableState<String> = mutableStateOf( viewModel.getLanguage() )

                Box(){
                    OutlinedButton(onClick = {
                        expanded.value = true
                    }) {
                        Row(Modifier.defaultMinSize()){
                            Text(text = currentLanguage.value , color = Color.Black)
                            Spacer(modifier = Modifier.width(15.dp))
                            Icon(imageVector = Icons.Default.ArrowDownward , contentDescription = null, tint = Color.Black, modifier = Modifier
                                .size(15.dp)
                                .padding(top = 5.dp) )
                        }

                    }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = {
                            expanded.value = false
                        },

                        ) {
                        viewModel.getPossibleLanguages().forEachIndexed() { index, str ->
                            DropdownMenuItem(onClick = {
                                viewModel.setLanguage(str)
                                currentLanguage.value = str
                                expanded.value = false
                            }) {
                                Text(text = str )
                            }
                        }
                    }

                }

                Text(stringResource(R.string.settings_account))

                val openDialog = remember { mutableStateOf(false)  }

                Button(onClick = {
                    openDialog.value = true
                }) {
                    Text("Logout")
                }

                val coroutineScope = rememberCoroutineScope()

                if (openDialog.value) {

                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = stringResource(R.string.settings_account_confirm))
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Red,
                                    contentColor = Color.White
                                ))
                            {
                                Text(stringResource(R.string.settings_logout_cancel))
                            }
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    viewModel.logout()
                                    coroutineScope.launch {
                                        delay(250)
                                        navController.navigate("login")
                                    }
                                    openDialog.value = false
                                }) {
                                Text("LOGOUT")
                            }
                        }
                    )
                }
            }
        }
    }
}

