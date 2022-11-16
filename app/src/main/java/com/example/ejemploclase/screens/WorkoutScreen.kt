package com.example.ejemploclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejemploclase.WorkoutViewModel
import com.example.ejemploclase.data.model.*


@Composable
fun WorkoutScreen(navController: NavHostController){
    com.example.ejemploclase.AppBar(navController) {
        setWorkout()
        WorkoutContent(workout)
    }
}

val workout  = Workout(4,"Pepito",3.3, Category(4,"Abs", "abdominal wok"), User(1,"Sancho","San","Agustin"," ", null ),true)


fun setWorkout(){
    val warmUpExercises = listOf(Exercise("Bicycle",180,0),Exercise("Jumping Jacks",60,0),
        Exercise("Piques",0,8)
    )
    val warmUpCycle = Cycle("Cardio","Warmup",3,0)
    warmUpCycle.setExercises(warmUpExercises)
    val commonExercises = listOf(Exercise("Bench Press",0,8),Exercise("MilitaryPress",0,8),Exercise("Tricep extensions",0,8))
    val commonCycle = Cycle("Musculacion","common",3,0)
    commonCycle.setExercises(commonExercises)
    val coolDownExercises = listOf(Exercise("Pecho",180,0),
        Exercise("Tricep",100,0),
        Exercise("Hombros",100,0)
    )
    val coolDownCycle = Cycle("Enlongacion","Cooldown",3,0)
    coolDownCycle.setExercises(coolDownExercises)
    workout.setCycles(arrayOf(warmUpCycle,commonCycle,coolDownCycle))
}

@Composable
fun WorkoutContent(workout: Workout?) {
    Box(
        modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 25.dp, 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .verticalScroll(rememberScrollState())
    ) {
        if (workout == null) {
            Column() {
                Row(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = "Choose a workout from your favorites to start training",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    imageVector = Icons.Default.Checklist,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        } else {
            val viewmodel = viewModel<WorkoutViewModel>()
            Column(modifier = Modifier.background(Color.LightGray)) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = workout.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                    Text(
                        text = workout.category.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                    Text(
                        text = "by ".plus(workout.user.username),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row() {
                    Column {
                        Row(modifier = Modifier.padding(15.dp, 10.dp)){
                            Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White) //Poner de vuelta el del theme
                                    .padding(15.dp)
                                    .clip(RoundedCornerShape(10.dp))){
                                WarmUpCycle(cycle = workout.getWarmupCycle(), viewmodel = viewmodel)
                            }
                        }
                        Row(modifier = Modifier.padding(15.dp, 10.dp)){
                            Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White) //Poner de vuelta el del theme
                                    .padding(15.dp)
                                    .clip(RoundedCornerShape(10.dp))){
                                CommonCycle(cycle = workout.getCommonCycle(), viewmodel = viewmodel)
                            }
                        }
                        Row(modifier = Modifier.padding(15.dp, 10.dp)){
                            Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White) //Poner de vuelta el del theme
                                    .padding(15.dp)
                                    .clip(RoundedCornerShape(10.dp))){
                                CooldownCycle(cycle = workout.getCooldownCycle(), viewmodel = viewmodel)
                            }
                        }
                        if(viewmodel.isDone){
                            Row(modifier = Modifier.padding(15.dp,10.dp)){
                                TextButton(onClick = { /*TODO pushearlo a la view de favs*/ },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary,
                                        contentColor = Color.White),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text(
                                        text="Finish Workout",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun WarmUpCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeWarmup(cycle.getRepetitions(),cycle.getExercises()!!.size)
    Column() {
        Row(modifier = Modifier.padding(15.dp, 5.dp)) {
            Text(
                text = cycle.getName(),
                fontSize = 23.sp
            )
        }
        var i = 0
        while(i < cycle.getRepetitions()){
            Column(modifier = Modifier.padding(horizontal = 15.dp)){
                Text(
                    text = (i+1).toString().plus(" rep"),
                    fontSize = 23.sp
                )
                for(exercise in cycle.getExercises()!!){
                    val checkedState = rememberSaveable { mutableStateOf(false) }
                    Row(modifier = Modifier.padding(15.dp,3.dp)){
                        LabelledCheckbox(checked = checkedState.value,
                            onCheckedChange = { if(it){
                                checkedState.value = it
                                viewmodel.decrementExerciseWarmup()
                            } /*else no permito que se destickee*/ },
                            label = exercise.getName())
                    }
                }
            }
            i++
        }
    }
}

@Composable
fun CommonCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeCommon(cycle.getRepetitions(),cycle.getExercises()!!.size)
    Column() {
        Row(modifier = Modifier.padding(15.dp, 5.dp)) {
            Text(
                text = cycle.getName(),
                fontSize = 23.sp
            )
        }
        var i = 0
        while(i < cycle.getRepetitions()){
            Column(modifier = Modifier.padding(horizontal = 15.dp)){
                Text(
                    text = (i+1).toString().plus(" rep"),
                    fontSize = 23.sp
                )
                for(exercise in cycle.getExercises()!!){
                    val checkedState = rememberSaveable { mutableStateOf(false) }
                    Row(modifier = Modifier.padding(15.dp,3.dp)){
                        LabelledCheckbox(checked = checkedState.value,
                            onCheckedChange = { if(it){
                                checkedState.value = it
                                viewmodel.decrementExerciseCommon()
                            } /*else no permito que se destickee*/ },
                            label = exercise.getName())
                    }
                }
            }
            i++
        }
    }
}

@Composable
fun CooldownCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeCooldown(cycle.getRepetitions(),cycle.getExercises()!!.size)
    Column() {
        Row(modifier = Modifier.padding(15.dp, 5.dp)) {
            Text(
                text = cycle.getName(),
                fontSize = 23.sp
            )
        }
        var i = 0
        while(i < cycle.getRepetitions()){
            Column(modifier = Modifier.padding(horizontal = 15.dp)){
                Text(
                    text = (i+1).toString().plus(" rep"),
                    fontSize = 23.sp
                )
                for(exercise in cycle.getExercises()!!){
                    val checkedState = rememberSaveable { mutableStateOf(false) }
                    Row(modifier = Modifier.padding(15.dp,3.dp)){
                        LabelledCheckbox(checked = checkedState.value,
                            onCheckedChange = { if(it){
                                checkedState.value = it
                                viewmodel.decrementExerciseCooldown()
                            } /*else no permito que se destickee*/ },
                            label = exercise.getName())
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

/*
* Row() {
                    Column {
                        for (cycle in workout.getCycles()!!) {
                            Row(modifier = Modifier.padding(15.dp, 10.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White) //Poner de vuelta el del theme
                                        .padding(15.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                ) {
                                    Column() {
                                        Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                                            Text(
                                                text = cycle.getName(),
                                                fontSize = 23.sp
                                            )
                                            Spacer(modifier = Modifier.weight(1f))
                                            Text(
                                                text = reps.toString(),
                                                fontSize = 23.sp
                                            )
                                        }
                                        for(exercise in cycle.getExercises()!!){
                                            val checkedState = rememberSaveable { mutableStateOf(false) }
                                            Row(modifier = Modifier.padding(15.dp,3.dp)){
                                                LabelledCheckbox(checked = checkedState.value,
                                                    onCheckedChange = { if(checkedState.value == false){
                                                                            checkedState.value = it
                                                                            viewmodel.decrement()
                                                                      }},
                                                    label = exercise.getName())
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }*/