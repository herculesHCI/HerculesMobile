package com.example.ejemploclase.data.network.model

import com.example.ejemploclase.data.model.Cycle
import com.google.gson.annotations.SerializedName

class NetworkCycle (

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("detail")
    var detail: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("order")
    var order: Int,
    @SerializedName("repetitions")
    var repetitions: Int

) {
    fun asModel() : Cycle {
        return Cycle(id,
            name,
            detail,
            type,
            order,
            repetitions)
    }
}