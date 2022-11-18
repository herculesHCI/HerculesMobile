package com.example.ejemploclase.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemploclase.data.model.Sport
import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.util.SessionManager
import com.example.ejemploclase.data.repository.FavoriteRepository
import com.example.ejemploclase.data.repository.RoutineRepository
import com.example.ejemploclase.data.repository.SportRepository
import com.example.ejemploclase.data.repository.UserRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val routineRepository: RoutineRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.login(username, password) // TODO HARDCODDEOOOO
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = true
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun logout() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.logout()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = null
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            userRepository.getCurrentUser(uiState.currentUser == null)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getSports() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSports(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                sports = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.getSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addOrModifySport(sport: Sport) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            if (sport.id == null)
                sportRepository.addSport(sport)
            else
                sportRepository.modifySport(sport)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = response,
                sports = null
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun deleteSport(sportId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            sportRepository.deleteSport(sportId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentSport = null,
                sports = null
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getRoutines(orderBy: String?,direction: String?,categoryId: Int?) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutines(true,orderBy,direction,categoryId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getRoutine(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutine(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getCompleteRoutine(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getCompleteRoutine(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun getFavorites() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favoriteRepository.getFavourites()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                favouritesRoutines = response,
                favChanged = false
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun markFavorite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favoriteRepository.markFavourite(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                favChanged = true
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun deleteFavorite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            favoriteRepository.deleteFavourite(routineId)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                favChanged = true
            )
            uiState.favouritesRoutines?.drop(routineId)
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun makeReview(routineId: Int,rating: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.makeRoutineReview(routineId,rating)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                favChanged = true
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun isFavourite(routine: Workout) : Boolean {
        return favoriteRepository.isFavourite(routine)
    }

    private var settedLanguage = false
    private var language = getLanguage()


    fun getLanguage(): String{
        if(!settedLanguage){
            setLanguage(Locale.getDefault().getLanguage())
            settedLanguage = true
        }
        return language;
    }


    fun setLanguage( lang : String ){
        /*val langOptions =  arrayOf("en","es")
        for(l in langOptions){
            if( lang == l ){
                this.language = l;
                return;
            }
        }*/
        language = lang
    }

    val languageOptions =  arrayOf("en","es")
    fun getPossibleLanguages(): Array<String> {
        return languageOptions
    }


}