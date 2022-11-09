package com.example.ejemploclase

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ejemploclase.ui.theme.EjemploClaseTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploClaseTheme {
                navController = rememberNavController()
                SetupGraph(navController = navController)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){view,insets ->
            val bottom=insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom=bottom)
            insets
        }

    }
}


//
//@Composable
//fun Greeting(name: String) {
//    var expanded by remember { mutableStateOf(false) }
//    Row {
//        Column(modifier = Modifier.padding(24.dp)) {
//            Text(text = "Hello, ")
//            Text(text = name)
//            OutlinedButton(onClick = { expanded = !expanded }) {
//                Text(if (expanded) "Show less" else "Show more")
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    EjemploClaseTheme {
//        LogInScreen(listOf("World", "Compose"))
//    }
//}