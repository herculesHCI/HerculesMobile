package com.example.ejemploclase

import android.app.Application
import com.example.ejemploclase.data.network.SportRemoteDataSource
import com.example.ejemploclase.data.network.UserRemoteDataSource
import com.example.ejemploclase.data.network.api.RetrofitClient
import com.example.ejemploclase.data.network.util.SessionManager
import com.example.ejemploclase.data.repository.SportRepository
import com.example.ejemploclase.data.repository.UserRepository

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)
}