package com.example.ejemploclase.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ejemploclase.graphs.RootNavigationGraph
import com.example.ejemploclase.ui.theme.EjemploClaseTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            EjemploClaseTheme {
                RootNavigationGraph(navController = rememberNavController())
            }//La main screen deberia recibir el siguiente parametro
            //(
            //    viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
            //)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){view,insets ->
            val bottom=insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom=bottom)
            insets
        }

    }
}
