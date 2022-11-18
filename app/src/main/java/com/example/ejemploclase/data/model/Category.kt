package com.example.ejemploclase.data.model

import com.example.ejemploclase.data.network.model.NetworkCategory

data class Category(
    var id: Int,
    var name: String,
    var detail: String
) {
    fun toNetworkCategory() : NetworkCategory {
        return NetworkCategory(name,detail)
    }
}