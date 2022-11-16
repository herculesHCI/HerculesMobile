package com.example.ejemploclase.data.network.api

import com.example.ejemploclase.data.network.model.NetworkFavourite
import com.example.ejemploclase.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiFavoriteService {

    @GET("favourites")
    suspend fun getFavourites() : Response<NetworkPagedContent<NetworkFavourite>>

    @POST("favourites/{routineId}")
    suspend fun markFavourite(@Path("routineId") routineId: Int) : Response<NetworkFavourite>

    @DELETE("favourites/{routineId}")
    suspend fun deleteFavourite(@Path("routineId") routineId: Int) : Response<NetworkFavourite>
}