package com.example.ejemploclase.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkReview (
    @SerializedName("score")
    var score: Int?,
    @SerializedName("review")
    var review: String,
) {

}