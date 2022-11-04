package com.example.ejemploclase

sealed class Screen(val route: String){
    object Login:Screen(route="loginScreen")
}
