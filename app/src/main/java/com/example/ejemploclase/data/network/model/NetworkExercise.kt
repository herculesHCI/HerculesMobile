package com.example.ejemploclase.data.network.model

import com.example.ejemploclase.data.model.BaseExercise
import com.example.ejemploclase.data.model.Exercise
import com.google.gson.annotations.SerializedName

class NetworkExercise (
    @SerializedName("order")
    var order: Int,
    @SerializedName("duration")
    var name: Int,
    @SerializedName("repetitions")
    var repetitions: Int,
    @SerializedName("exercise")
    var baseExercise: BaseExercise
) {
    fun asModel() : Exercise {
        return Exercise(
            order,
            name,
            repetitions,
            baseExercise
        )
    }
}