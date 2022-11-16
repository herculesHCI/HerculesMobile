package com.example.ejemploclase.data.network

import com.example.ejemploclase.data.network.api.ApiFavoriteService
import com.example.ejemploclase.data.network.model.NetworkFavourite
import com.example.ejemploclase.data.network.model.NetworkPagedContent

class FavoriteRemoteDataSource(
    private val apiFavouriteService: ApiFavoriteService
) : RemoteDataSource() {

    suspend fun getFavourites() : NetworkPagedContent<NetworkFavourite> {
        return handleApiResponse {
            apiFavouriteService.getFavourites()
        }
    }

    suspend fun markFavourite(routineId: Int) : NetworkFavourite {
        return handleApiResponse {
            apiFavouriteService.markFavourite(routineId)
        }
    }

    suspend fun deleteFavourite(routineId: Int) : NetworkFavourite {
        return handleApiResponse {
            apiFavouriteService.deleteFavourite(routineId)
        }
    }
}