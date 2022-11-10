package com.example.ejemploclase

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ejemploclase.ui.theme.EjemploClaseTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploClaseTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){view,insets ->
            val bottom=insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom=bottom)
            insets
        }

    }
}
