package com.example.ejemploclase.data.network

import com.example.ejemploclase.data.network.api.ApiRoutineService
import com.example.ejemploclase.data.network.model.NetworkPagedContent
import com.example.ejemploclase.data.network.model.NetworkRoutine
import com.example.ejemploclase.data.network.model.NetworkSport

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines(orderBy: String?, direction: String?, categoryId: Int?) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(categoryId,orderBy,direction,)
        }
    }

    suspend fun getSport(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }
}