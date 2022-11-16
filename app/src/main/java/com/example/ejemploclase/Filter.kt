package com.example.ejemploclase

data class Filter(
    var orderBy: String = "date",
    var direction: String = "asc",
    var categoryId: Int? = null,
    var title: String = "Most Recent Created",
)