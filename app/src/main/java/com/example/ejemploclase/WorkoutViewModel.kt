package com.example.ejemploclase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WorkoutViewModel: ViewModel() {
    private var exercisesLeftInWarmup = MutableStateFlow(-1)
    private var exercisesLeftInCommon = MutableStateFlow(-1)
    private var exercisesLeftInCooldown = MutableStateFlow(-1)
    var isDone by mutableStateOf(false)
        private set

    fun initializeWarmup(cycleReps: Int,exerciseAmount: Int) {
        if(exercisesLeftInWarmup.value == -1){
            exercisesLeftInWarmup.value = cycleReps*exerciseAmount
        }
    }
    fun initializeCommon(cycleReps: Int,exerciseAmount: Int) {
        if(exercisesLeftInCommon.value == -1){
            exercisesLeftInCommon.value = cycleReps*exerciseAmount
        }
    }
    fun initializeCooldown(cycleReps: Int,exerciseAmount: Int) {
        if(exercisesLeftInCooldown.value == -1){
            exercisesLeftInCooldown.value = cycleReps*exerciseAmount
        }
    }
    fun decrementExerciseWarmup() {
        exercisesLeftInWarmup.value--
        if(isWorkoutDone()){
            isDone = true
        }
    }
    fun decrementExerciseCommon() {
        exercisesLeftInCommon.value--
        if(isWorkoutDone()){
            isDone = true
        }
    }
    fun decrementExerciseCooldown() {
        exercisesLeftInCooldown.value--
        if(isWorkoutDone()){
            isDone = true
        }
    }
    fun isWorkoutDone() : Boolean {
        return exercisesLeftInWarmup.value == 0 && exercisesLeftInCommon.value == 0 && exercisesLeftInCooldown.value == 0
    }
}