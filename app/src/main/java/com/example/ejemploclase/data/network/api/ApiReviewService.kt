package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkReview
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiReviewService {
    @POST("reviews/{routineId}")
    suspend fun makeRoutineReview(@Path("routineId") routineId: Int, @Body review: NetworkReview) : Response<Unit>
}