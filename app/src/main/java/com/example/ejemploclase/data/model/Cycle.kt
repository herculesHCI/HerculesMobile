package com.example.ejemploclase.data.model

import com.example.ejemploclase.data.model.Exercise

data class Cycle (
        var id: Int,
        var name: String,
        var detail: String,
        var type: String,
        var order: Int,
        var repetitions: Int,
        ) {
        private var exercises: List<Exercise>? = null

        fun hasExercises() : Boolean {
                return exercises != null
        }
        fun getExercises() : List<Exercise>? {
                return exercises
        }
        fun setExercises(exercises: List<Exercise>?) {//la lista que recibo estaria ordenada
                this.exercises = exercises
        }
}