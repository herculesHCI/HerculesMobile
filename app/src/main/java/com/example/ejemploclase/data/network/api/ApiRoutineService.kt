package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkPagedContent
import com.example.ejemploclase.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines(@Query("categoryId") categoryId: Int? = null,@Query("orderBy") orderBy: String? = "--",@Query("direction") direction: String? = null) : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>
}