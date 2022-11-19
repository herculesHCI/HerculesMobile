package com.example.ejemploclase.data.repository

import com.example.ejemploclase.data.model.Category
import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val sportsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var routines: List<Workout> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false,orderBy: String?,direction: String?, categoryId: Int?): List<Workout> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines(orderBy,direction, categoryId)
            // Thread-safe write to latestNews
            sportsMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }

        return sportsMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Workout {
        return remoteDataSource.getRoutine(routineId).asModel()
    }

    suspend fun makeRoutineReview(routineId: Int,rating: Int) {
        remoteDataSource.makeRoutineReview(routineId,rating)
    }

    suspend fun makeCategory(category: Category) {
        remoteDataSource.makeCategory(category)
    }

    suspend fun getCompleteRoutine(routineId: Int) : Workout {
        val workout = remoteDataSource.getRoutine(routineId).asModel()
        val resultCycles = remoteDataSource.getRoutineCycles(routineId)
        if(resultCycles.totalCount == 0) {
            workout.setCycles(null)
            return workout
        }
        val cycles = resultCycles.content.map { it.asModel() }
        for(cycle in cycles) {
            val resultExercises = remoteDataSource.getCycleExercises(cycle.id)
            if(resultExercises.totalCount != 0){
                val exercises = resultExercises.content.map { it.asModel() }
                cycle.setExercises(exercises)
            }
        }
        workout.setCycles(cycles.toTypedArray())
        return workout
    }
}