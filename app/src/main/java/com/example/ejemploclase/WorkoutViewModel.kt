package com.example.ejemploclase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutViewModel: ViewModel() {
    private val repsWarmup = MutableStateFlow(-1)
    private val repsCommon = MutableStateFlow(-1)
    private val repsCooldown = MutableStateFlow(-1)
    val repsCyclesWarmup = repsWarmup.asStateFlow()
    val repsCyclesCommon = repsCommon.asStateFlow()
    val repsCyclesCooldown = repsCooldown.asStateFlow()

    fun initializeWarmup(num: Int) {
        if(repsWarmup.value == -1){
            repsWarmup.value = num
        }
    }
    fun initializeCommon(num: Int) {
        if(repsCommon.value == -1){
            repsCommon.value = num
        }
    }
    fun initializeCooldown(num: Int) {
        if(repsCooldown.value == -1){
            repsCooldown.value = num
        }
    }
    fun decrementWarmup() {
        this.repsWarmup.value--
    }
    fun decrementCommon() {
        this.repsCommon.value--
    }
    fun decrementCooldown() {
        this.repsCooldown.value--
    }
}