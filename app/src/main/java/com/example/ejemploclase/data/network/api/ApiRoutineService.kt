package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkCycle
import com.example.ejemploclase.data.network.model.NetworkExercise
import com.example.ejemploclase.data.network.model.NetworkPagedContent
import com.example.ejemploclase.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines(@Query("categoryId") categoryId: Int? = null,@Query("orderBy") orderBy: String? = "--",@Query("direction") direction: String? = "--") : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(@Path("routineId") routineId: Int,@Query("size") size: Int = 20,@Query("orderBy") orderBy: String = "order",@Query("direction") direction: String = "asc") : Response<NetworkPagedContent<NetworkCycle>>

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int,@Query("size") size: Int = 20,@Query("orderBy") orderBy: String = "order",@Query("direction") direction: String = "asc") : Response<NetworkPagedContent<NetworkExercise>>
}