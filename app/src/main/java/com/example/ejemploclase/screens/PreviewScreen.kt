package com.example.ejemploclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import com.example.ejemploclase.ui.main.canGetRoutine


@Composable
fun PreviewScreen(navController: NavHostController , workoutId : Int?,viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
    factory = getViewModelFactory()
)
){
    com.example.ejemploclase.AppBar(navController) {
        PreviewContent(navController, workoutId, viewModel)
    }
}

@Composable
fun PreviewContent(navController: NavHostController,workoutId : Int?,viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
    factory = getViewModelFactory()
)
){
    if(workoutId != null){
        val uiState = viewModel.uiState
        if(uiState.currentRoutine == null){
            viewModel.getRoutine(workoutId)
        }
        if(viewModel.uiState.canGetRoutine){
            val workout = viewModel.uiState.currentRoutine
            Box( modifier = Modifier.background(MaterialTheme.colors.background)){
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(40.dp)
                    )
                }
                Card(shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 65.dp)
                        .defaultMinSize(),
                    backgroundColor = MaterialTheme.colors.primary
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    )
                    {
                        Row( modifier = Modifier
                            .fillMaxWidth()
                        ){
                            workout?.name?.let {
                                Text(
                                    text= it,
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer( modifier = Modifier
                                .weight(1f))
                            Text(
                                modifier = Modifier.padding(top=7.dp),
                                text= workout?.getScore().toString(),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(top = 5.dp),
                            )
                        }
                        workout?.category?.let { //Se hizo el chequeo antes
                            Text(                               //pero no permite hacerlo sin el let
                                modifier = Modifier.padding(top=7.dp),
                                text= it.name,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "by " + workout?.user?.username,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        if(workout?.hasCycles() == true){
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .padding(top = 15.dp)
                            ) {
                                workout.getCycles()?.forEach { cyc ->
                                    Text(
                                        text = cyc.getName(),
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    cyc.getExercises()?.forEach(){ ex ->
                                        Text(
                                            text = ex.getName(),
                                            fontSize = 20.sp,
                                        )
                                    }
                                    Spacer(modifier = Modifier
                                        .fillMaxWidth()
                                        .height(20.dp))
                                }
                            }
                        } else {
                            ErrorMessage("This workout doesn't have any cycles and/or exercises")
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    }
    ErrorMessage("There seems to be an error :/")
}

