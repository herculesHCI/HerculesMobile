package com.example.ejemploclase

class Cycle ( // No guardo el order, SIEMPRE PEDIR CYCLE EN ORDER ASC
        private var name: String,
        private var type: String,
        private var repetitions: Int,
        private var routineId: Number //Capaz no es util
        ) {
        private var exercises: List<Exercise>? = null

        fun getName() : String {
                return name
        }
        fun getType() : String {
                return type
        }
        fun getRepetitions() : Int {
                return repetitions
        }
        fun getExercises() : List<Exercise>? {
                return exercises
        }

        fun setExercises(exercises: List<Exercise>?) {//la lista que recibo estaria ordenada
                this.exercises = exercises
        }
}