package com.example.ejemploclase.data.model

data class Exercise ( // No guardo el order, SIEMPRE PEDIR CYCLE EXERCISES EN ORDER ASC
    var order: Int,
    var duration: Int,
    var repetitions: Int,
    var baseExercise: BaseExercise,
)