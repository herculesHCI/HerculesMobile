package com.example.ejemploclase.data.model

data class Workout(
    val id: Int,
    var name: String,
    var score: Number,
    var category: Category,
    var user: User,
    var isFav: Boolean? = false,
) {
    private var cycles: Array<Cycle>? = null

    fun getCycles() : Array<Cycle>? {
        return cycles
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