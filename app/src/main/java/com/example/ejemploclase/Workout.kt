package com.example.ejemploclase

class Workout(
    private var name: String,
    private var author: String,
    private var score: Number,
    private var categoryName: String,
    private var isFav: Boolean,
    private val id: Number
) {
    private var cycles: Array<Cycle>? = null

    fun getName() : String{
        return name
    }
    fun getAuthor() : String{
        return author
    }
    fun getScore() : Number{
        return score
    }
    fun getCategoryName() : String {
        return categoryName
    }
    fun getIsFav() : Boolean {
        return isFav
    }
    fun getId() : Number {
        return id
    }
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