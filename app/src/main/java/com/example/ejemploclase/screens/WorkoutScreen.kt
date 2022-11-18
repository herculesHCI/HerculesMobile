package com.example.ejemploclase.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejemploclase.data.model.*
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.screens.utils.ErrorMessage
import com.example.ejemploclase.screens.utils.shareWorkout
import com.example.ejemploclase.ui.main.MainViewModel
import com.example.ejemploclase.ui.main.canGetRoutine


@Composable
fun WorkoutScreen(navController: NavHostController, workoutId: Int ,viewModel: MainViewModel = viewModel(factory = getViewModelFactory()) ){
    com.example.ejemploclase.AppBarCompact(navController) {
        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                WorkoutContent(workoutId, navController, viewModel)
            }
            // Other wise
            else -> {
                WorkoutContent(workoutId, navController, viewModel)
            }
        }
    }
}

@Composable
fun WorkoutContent(workoutId: Int, navController: NavHostController, viewModel: MainViewModel) {
    var isDetailed = remember { mutableStateOf(false)  }
    if(workoutId == 0){
        ErrorMessage(message = "Choose a workout from your favorites to start training")
    } else {
        val uiState = viewModel.uiState
        if(!uiState.canGetRoutine && !viewModel.uiState.isFetching){
            viewModel.getCompleteRoutine(workoutId)
        }
        if(viewModel.uiState.canGetRoutine) {
            val workout = viewModel.uiState.currentRoutine
            Box(modifier = Modifier.fillMaxSize()){
                /**
                 * hasStarted has three posible values:
                 * 0 if the wokout wasnt started,
                 * 1 if it was started,
                 * 2 if its finished
                 */
                val hasStarted = remember { mutableStateOf(0) }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(horizontal = 25.dp, 15.dp)
                        .padding(bottom = 60.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colors.primary)
                    ) {
                        if( hasStarted.value==1 || hasStarted.value==0 ){
                            Row(
                                modifier = Modifier.padding(15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                workout?.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.background
                                    )
                                }

                                Spacer(Modifier.weight(1f))

                                Button(
                                    onClick = {
                                        isDetailed.value = !isDetailed.value
                                    },
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                                ){
                                    var str = "Simple Mode"
                                    if(isDetailed.value){
                                        str = "Detailed Mode"
                                    }
                                    Text(str)
                                }
                            }
                            Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                                workout?.category?.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.background
                                    )
                                }
                            }
                            Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                                Text(
                                    text = "by ".plus(workout?.user?.username),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.background
                                )
                            }
                            Row() {
                                Column {
                                    if (workout != null) {
                                        if(workout.hasCycles()){
                                            for(cycle in workout.getCycles()!!) {
                                                Row(modifier = Modifier.padding(15.dp, 10.dp)){
                                                    Box(modifier = Modifier
                                                        .clip(RoundedCornerShape(10.dp))
                                                        .fillMaxWidth()
                                                        .background(MaterialTheme.colors.background)
                                                        .padding(15.dp)
                                                    ){
                                                        Cycle(cycle = cycle, isDetailed.value)
                                                    }
                                                }
                                            }
                                        } else {
                                            ErrorMessage(message = "This workout doesn't have any cycles and/or exercises")
                                        }
                                    }
                                }
                            }
                            if(hasStarted.value == 1 ){  //TODO center correctly
                                Spacer(Modifier.weight(1f))
                                Row(){
                                    Button(
                                        onClick = {
                                            hasStarted.value = 2
                                        }) {
                                        Text(text = "Finish Workout",
                                            fontSize = 30.sp)
                                    }
                                }
                                Spacer(Modifier.weight(1f))
                            }


                        }
                        if(hasStarted.value == 2 ){
                            Column(modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colors.primary)
                                .padding(15.dp)
                            ) {
                                val stars = remember { mutableStateOf(0)}
                                Row(){
                                    IconButton(onClick = {
                                        hasStarted.value = 1
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null,
                                            tint = MaterialTheme.colors.background,
                                            modifier = Modifier
                                                .padding(15.dp)
                                                .size(40.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    val context = LocalContext.current
                                    IconButton(onClick = {
                                        if (workout != null) {
                                            shareWorkout(context,workout.id)
                                        }
                                    }) {
                                        Icon(imageVector = Icons.Default.Share,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(15.dp)
                                                .size(40.dp)
                                        )
                                    }
                                    IconButton(onClick = {
                                        navController.navigate("discover")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Close,
                                            contentDescription = null,
                                            tint = MaterialTheme.colors.background,
                                            modifier = Modifier
                                                .padding(15.dp)
                                                .size(40.dp)
                                        )
                                    }
                                }
                                Text(text="Workout Finished",
                                    color = MaterialTheme.colors.background)
                                workout?.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.background
                                    )
                                }
                                Text(
                                    text = "by ".plus(workout?.user?.username),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.background
                                )
                                Text(
                                    text = "Rate the Workout!",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.background
                                )
                                Row(){
                                    for( i in 1..5 ){
                                        IconButton(onClick = {
                                            stars.value = i
                                        }) {
                                            var col = MaterialTheme.colors.background;
                                            if (stars.value >= i ) {
                                                col = MaterialTheme.colors.secondary
                                            }
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = null,
                                                tint =  col ,
                                                modifier = Modifier
                                                    .padding(15.dp)
                                                    .size(30.dp)
                                            )
                                        }
                                    }
                                }
                                val mContext = LocalContext.current
                                Button(
                                    onClick = {
                                        workout?.id?.let { viewModel.makeReview(it,stars.value*2) }
                                        Toast.makeText(mContext, "Review Submitted", Toast.LENGTH_LONG).show()
                                        navController.navigate("discover")
                                    }) {
                                    Text(text = "Sumbit Rating",
                                        fontSize = 30.sp,
                                        color = MaterialTheme.colors.background)
                                }
                            }
                        }
                    }
                }
                if( hasStarted.value == 0 ){
                    FloatingActionButton(
                        onClick = {
                            hasStarted.value = 1
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.background,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 75.dp)
                            .padding(horizontal = 15.dp)
                            .size(65.dp)
                    ) {
                        // on below line we are
                        // adding icon for button.
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun Cycle(cycle: Cycle, isDetailed: Boolean){
    Column() {
        Row(modifier = Modifier.padding(15.dp, 5.dp)) {
            Text(
                text = cycle.name,
                fontSize = 23.sp
            )
        }
        var i = 0
        while(i < cycle.repetitions){
            Column(modifier = Modifier.padding(horizontal = 15.dp)){
                Text(
                    text = (i+1).toString().plus(" rep"),
                    fontSize = 23.sp
                )
                for(exercise in cycle.getExercises()!!){
                    val checkedState = rememberSaveable { mutableStateOf(false) }
                    Row(modifier = Modifier.padding(15.dp,3.dp)){
                        var str : String = ""
                        if(isDetailed){
                             str = "   " + exercise.duration.toString() + "s x " + exercise.repetitions.toString() + " rep"
                        }

                        LabelledCheckbox(checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            label = exercise.baseExercise.name + str )
                    }
                    if( isDetailed ){
                        Text( exercise.baseExercise.detail , fontSize = 12.sp , fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraLight )
                    }
                }
            }
            i++
        }
    }
}

@Composable
fun LabelledCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Spacer(Modifier.weight(1f))
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
    }
}