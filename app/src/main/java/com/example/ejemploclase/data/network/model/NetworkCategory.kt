package com.example.ejemploclase.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCategory (
    @SerializedName("name")
    val name: String,
    @SerializedName("detail")
    val review: String?
        )