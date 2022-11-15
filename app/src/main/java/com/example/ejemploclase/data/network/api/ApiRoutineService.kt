package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkPagedContent
import com.example.ejemploclase.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines(@Path("orderBy") filter: String? = null,@Path("direction") dir: String? = null,@Path("categoryId") categoryId: Int? = null) : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>
}