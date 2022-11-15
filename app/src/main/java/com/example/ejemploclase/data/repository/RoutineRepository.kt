package com.example.ejemploclase.data.repository

import com.example.ejemploclase.data.model.Sport
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
        return remoteDataSource.getSport(routineId).asModel()
    }
}