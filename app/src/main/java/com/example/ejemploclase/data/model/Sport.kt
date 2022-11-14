package com.example.ejemploclase.data.model

import com.example.ejemploclase.data.network.model.NetworkSport

class Sport(
    var id: Int? = null,
    var name: String,
    var detail: String? = null
) {
    fun asNetworkModel(): NetworkSport {
        return NetworkSport(
            id = id,
            name = name,
            detail = detail
        )
    }
}