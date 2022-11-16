package com.example.ejemploclase.ui.main

import com.example.ejemploclase.data.model.Sport
import com.example.ejemploclase.data.model.User
import com.example.ejemploclase.data.model.Workout

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
    val routines: List<Workout>? = null,
    val currentRoutine: Workout? = null,
    val favouritesRoutines: List<Workout>? = null,
    val message: String? = null
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport
val MainUiState.canGetRoutines: Boolean get() = isAuthenticated && routines != null
val MainUiState.canGetRoutine: Boolean get() = isAuthenticated && currentRoutine != null
val MainUiState.canGetFavourites: Boolean get() = isAuthenticated && favouritesRoutines != null
val MainUiState.canMarkFavourite: Boolean get() = isAuthenticated
val MainUiState.canDeleteFavourite: Boolean get() = isAuthenticated