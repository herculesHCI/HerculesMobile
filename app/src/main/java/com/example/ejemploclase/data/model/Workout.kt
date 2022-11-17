package com.example.ejemploclase.data.model

data class Workout(
    val id: Int,
    val name: String,
    private val score: Number,
    val category: Category,
    val user: User,
    val isFav: Boolean? = false,
) {
    private var cycles: Array<Cycle>? = null

    fun getScore() : Float {
        return score.toFloat()/2
    }
    fun getCycles() : Array<Cycle>? {
        return cycles
    }
    fun hasCycles() : Boolean {
        return cycles != null
    }
    fun getWarmupCycle() : Cycle {
        return cycles!![0]
    }
    fun getCommonCycle() : Cycle {
        return cycles!![1]
    }
    fun getCooldownCycle() : Cycle {
        return cycles!![2]
    }
    fun setCycles(cycles: Array<Cycle>?) {//la lista que recibo estaria ordenada
        this.cycles = cycles
    }
}