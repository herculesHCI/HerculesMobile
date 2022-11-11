package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WorkoutScreen(){
    val workout  = Workout("Diego´s Workout","Ppeito",3.3,"Upper",true,0)
    val warmUpExercises = listOf(Exercise("Bicycle",180,0),Exercise("Jumping Jacks",60,0))
    val warmUpCycle = Cycle("Cardio","Warmup",3,0)
    warmUpCycle.setExercises(warmUpExercises)
    val commonExercises = listOf(Exercise("Bench Press",0,8),Exercise("MilitaryPress",0,8),Exercise("Tricep extensions",0,8))
    val commonCycle = Cycle("Cardio","common",3,0)
    commonCycle.setExercises(commonExercises)
    val coolDownExercises = listOf(Exercise("Enlongacion",180,0))
    val coolDownCycle = Cycle("Enlongacion","Cooldown",3,0)
    coolDownCycle.setExercises(coolDownExercises)
    workout.setCycles(listOf(warmUpCycle,commonCycle,coolDownCycle))
    WorkoutContent(workout)
}


@Composable
fun WorkoutContent(workout: Workout?){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)
        .verticalScroll(rememberScrollState())){
        if(workout == null){
            Column(){
                Row(modifier = Modifier.padding(15.dp)){
                    Text(text= "Choose a workout from your favorites to start training",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold)
                }
                Icon(imageVector = Icons.Default.Checklist,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        } else {
            Box(modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState())){
                Column(){
                    Row(modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Text(text= workout.getName(),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold)
                        Spacer( modifier = Modifier
                            .weight(1f))
                        Text(text = workout.getCategoryName(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    for(cycle in workout.getCycles()!!){
                        Row(){
                            Text(text = cycle.getName(),
                                fontSize = 11.sp)
                            Spacer( modifier = Modifier
                                .weight(1f))
                            Text(text = cycle.getRepetitions().toString(),
                                fontSize = 11.sp)
                            for(exercise in cycle.getExercises()!!){
                                Row(modifier = Modifier.padding(10.dp)){
                                    LabelledCheckbox(checked = false, onCheckedChange = {/*TODO*/}, label = exercise.getName())
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

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors
        )
        Spacer(Modifier.width(32.dp))
        Text(label)
    }
}