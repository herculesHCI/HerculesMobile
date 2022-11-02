package com.example.ejemploclase

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemploclase.ui.theme.EjemploClaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploClaseTheme {
                MainScreen(listOf("World","Compose"))
            }
        }
    }
}

@Composable
fun MainScreen(names: List<String>) {
    // A surface container using the 'background' color from the theme
    Surface(//El surface es el equivalente al div
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Column {
            for(name in names){
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false)}
    Row{
        Column(modifier = Modifier.padding(24.dp)){
            Text(text = "Hello, ")
            Text(text = name)
            OutlinedButton(onClick = { expanded = !expanded}) {
                Text(if(expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EjemploClaseTheme {
        MainScreen(listOf("World","Compose"))
    }
}