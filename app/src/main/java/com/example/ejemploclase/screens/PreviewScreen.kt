package com.example.ejemploclase.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemploclase.R
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.screens.utils.ErrorMessage
import com.example.ejemploclase.screens.utils.shareWorkout
import com.example.ejemploclase.ui.main.MainViewModel
import com.example.ejemploclase.ui.main.canGetRoutine


@Composable
fun PreviewScreen(navController: NavHostController , workoutId : Int?,viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
    factory = getViewModelFactory()
)
){
    com.example.ejemploclase.AppBarCompact(navController) {
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
        val workout = uiState.currentRoutine
        if(uiState.currentRoutine == null){
            if(!viewModel.uiState.isFetching) {
                viewModel.getCompleteRoutine(workoutId)
            }
        }
        if(viewModel.uiState.canGetRoutine){
            Box( modifier = Modifier.background(MaterialTheme.colors.background)){
                Row(){
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
                    Spacer( modifier = Modifier.weight(1f))
                    val context = LocalContext.current
                    IconButton(onClick = {
                        if (workout != null) {
                            shareWorkout(context,workout.id)
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(40.dp)
                        )
                    }
                    val mContext = LocalContext.current
                    val prev_added_fav = stringResource(R.string.prev_added_fav)
                    IconButton(onClick = {
                        workout?.id?.let { viewModel.markFavorite(it) }
                        Toast.makeText(mContext, prev_added_fav , Toast.LENGTH_LONG).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(40.dp)
                        )
                    }
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
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Spacer( modifier = Modifier
                                .weight(1f))
                            Text(
                                modifier = Modifier.padding(top=7.dp),
                                text= workout?.getScore().toString(),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Icon(
                                imageVector = Icons.Default.StarBorder,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(top = 5.dp),
                                tint = Color.Black
                            )
                        }
                        val let = workout?.category?.let { //Se hizo el chequeo antes
                            Text(                               //pero no permite hacerlo sin el let
                                modifier = Modifier.padding(top = 7.dp),
                                text = it.name,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Text(
                            text = stringResource(R.string.by) + " " + workout?.user?.username,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = Color.Black
                        )
                        if (workout != null) {
                            if(workout.hasCycles()){
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(top = 15.dp)
                                ) {
                                    workout.getCycles()?.forEach { cyc ->
                                        Text(
                                            text = cyc.name,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.Black
                                        )
                                        if(cyc.hasExercises()){
                                            cyc.getExercises()?.forEach(){ ex ->
                                                Text(
                                                    text = ex.baseExercise.name,
                                                    fontSize = 20.sp,
                                                    color = Color.Black
                                                )
                                            }
                                        } else {
                                            ErrorMessage(message = stringResource(R.string.prev_err_no_exercises))
                                        }
                                        Spacer(modifier = Modifier
                                            .fillMaxWidth()
                                            .height(20.dp))
                                    }
                                }
                            } else {
                                ErrorMessage(stringResource(R.string.prev_err_no_cycles))
                            }
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }
    } else {
        ErrorMessage(stringResource(R.string.prev_err))
    }
}

