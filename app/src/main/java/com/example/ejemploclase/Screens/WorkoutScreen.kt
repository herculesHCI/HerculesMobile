package com.example.ejemploclase

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun WorkoutScreen(){
    setWorkout()
    WorkoutContent(workout)
}

val workout  = Workout("Diego´s Workout","Ppeito",3.3,"Upper",true,0)

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
                        text = workout.getName(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                    Text(
                        text = workout.getCategoryName(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(modifier = Modifier.padding(15.dp, 5.dp)) {
                    Text(
                        text = "by ".plus(workout.getAuthor()),
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
                                warmUpCycle(cycle = workout.getWarmupCycle(), viewmodel = viewmodel)
                            }
                        }
                        Row(modifier = Modifier.padding(15.dp, 10.dp)){
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White) //Poner de vuelta el del theme
                                .padding(15.dp)
                                .clip(RoundedCornerShape(10.dp))){
                                commonCycle(cycle = workout.getCommonCycle(), viewmodel = viewmodel)
                            }
                        }
                        Row(modifier = Modifier.padding(15.dp, 10.dp)){
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White) //Poner de vuelta el del theme
                                .padding(15.dp)
                                .clip(RoundedCornerShape(10.dp))){
                                cooldownCycle(cycle = workout.getCooldownCycle(), viewmodel = viewmodel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun warmUpCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeWarmup(cycle.getExercises()!!.size)
    val reps by viewmodel.repsCyclesWarmup.collectAsState()
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
                        viewmodel.decrementWarmup()
                    }},
                    label = exercise.getName())
            }
        }
    }
}

@Composable
fun commonCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeCommon(cycle.getExercises()!!.size)
    val reps by viewmodel.repsCyclesCommon.collectAsState()
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
                        viewmodel.decrementCommon()
                    }},
                    label = exercise.getName())
            }
        }
    }
}

@Composable
fun cooldownCycle(cycle: Cycle,viewmodel: WorkoutViewModel){
    viewmodel.initializeCooldown(cycle.getExercises()!!.size)
    val reps by viewmodel.repsCyclesCooldown.collectAsState()
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
                        viewmodel.decrementCooldown()
                    }},
                    label = exercise.getName())
            }
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