package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkCategory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiCategoryService {
    @POST("categories")
    suspend fun makeCategory(@Body category: NetworkCategory) : Response<Unit>
}