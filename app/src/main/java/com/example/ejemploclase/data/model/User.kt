package com.example.ejemploclase.data.model

import java.util.*

data class User(
    var id: Int?,
    var username: String,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    var lastActivity: Date? = null
)