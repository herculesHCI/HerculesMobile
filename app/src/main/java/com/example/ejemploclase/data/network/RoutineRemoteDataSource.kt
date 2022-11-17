package com.example.ejemploclase.data.network

import com.example.ejemploclase.data.network.api.ApiReviewService
import com.example.ejemploclase.data.network.api.ApiRoutineService
import com.example.ejemploclase.data.network.model.*

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService,
    private val apiReviewService: ApiReviewService
) : RemoteDataSource() {

    suspend fun getRoutines(orderBy: String?, direction: String?, categoryId: Int?) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(categoryId,orderBy,direction,)
        }
    }

    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }

    suspend fun makeRoutineReview(routineId: Int,rating: Int) {
        handleApiResponse {
            apiReviewService.makeRoutineReview(routineId,NetworkReview(rating,""))
        }
    }

    suspend fun getRoutineCycles(routineId: Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiRoutineService.getRoutineCycles(routineId)
        }
    }

    suspend fun getCycleExercises(cycleId: Int) : NetworkPagedContent<NetworkExercise> {
        return handleApiResponse {
            apiRoutineService.getCycleExercises(cycleId)
        }
    }
}