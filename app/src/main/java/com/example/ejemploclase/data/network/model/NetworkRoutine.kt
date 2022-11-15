package com.example.ejemploclase.data.network.model

import com.example.ejemploclase.data.model.Category
import com.example.ejemploclase.data.model.User
import com.example.ejemploclase.data.model.Workout
import com.google.gson.annotations.SerializedName

class NetworkRoutine (

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("score")
    var score: Int,
    @SerializedName("user")
    var user: User, //User o NetworkUser?
    @SerializedName("category")
    var category: Category

) {
    fun asModel() : Workout {
        return Workout(id,
            name,
            score,
            category,
            user)
    }
}