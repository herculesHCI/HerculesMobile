package com.example.ejemploclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemploclase.ui.theme.EjemploClaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploClaseTheme {
                LogInScreen(listOf("World","Compose"))
            }
        }
    }
}

@Composable
fun LogInScreen(names: List<String>) {
    // A surface container using the 'background' color from the theme
    Surface(//El surface es el equivalente al div
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Image(painter = painterResource(id = R.drawable.hercules),
            contentDescription = null,
            alignment = Alignment.TopEnd,
            modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp))
        Row (modifier = Modifier.padding(vertical = 50.dp).sizeIn(minHeight = 200.dp)) {

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
        LogInScreen(listOf("World","Compose"))
    }
}