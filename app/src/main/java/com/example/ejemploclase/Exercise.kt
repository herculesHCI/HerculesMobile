package com.example.ejemploclase

class Exercise ( // No guardo el order, SIEMPRE PEDIR CYCLE EXERCISES EN ORDER ASC
    private var name: String,
    private var duration: Number,
    private var repetitions: Number,
        ){
    fun getName() : String {
        return name
    }
    fun getDuration() : Number {
        return duration
    }
    fun getRepetitions() : Number {
        return repetitions
    }
}