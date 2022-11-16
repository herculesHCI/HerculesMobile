package com.example.ejemploclase

import android.app.Application
import com.example.ejemploclase.data.network.FavoriteRemoteDataSource
import com.example.ejemploclase.data.network.RoutineRemoteDataSource
import com.example.ejemploclase.data.network.SportRemoteDataSource
import com.example.ejemploclase.data.network.UserRemoteDataSource
import com.example.ejemploclase.data.network.api.RetrofitClient
import com.example.ejemploclase.data.network.util.SessionManager
import com.example.ejemploclase.data.repository.FavoriteRepository
import com.example.ejemploclase.data.repository.RoutineRepository
import com.example.ejemploclase.data.repository.SportRepository
import com.example.ejemploclase.data.repository.UserRepository

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val favouriteRemoteDataSource: FavoriteRemoteDataSource
        get() = FavoriteRemoteDataSource(RetrofitClient.getApiFavouriteService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)

    val favouriteRepository: FavoriteRepository
        get() = FavoriteRepository(favouriteRemoteDataSource)
}