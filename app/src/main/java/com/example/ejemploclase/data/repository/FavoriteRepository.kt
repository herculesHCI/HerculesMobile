package com.example.ejemploclase.data.repository

import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.FavoriteRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FavoriteRepository(
    private val remoteDataSource: FavoriteRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val sportsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var routines: List<Workout> = emptyList()

    suspend fun getFavourites(refresh: Boolean = false): List<Workout> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getFavourites()
            // Thread-safe write to latestNews
            sportsMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }

        return sportsMutex.withLock { this.routines }
    }

    suspend fun markFavourite(routineId: Int) {
       remoteDataSource.markFavourite(routineId)
    }

    suspend fun deleteFavourite(routineId: Int) {
        remoteDataSource.deleteFavourite(routineId)
    }
}