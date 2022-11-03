package com.example.ejemploclase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemploclase.ui.theme.EjemploClaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploClaseTheme {
                LogInScreen(listOf("World", "Compose"))
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


        Column(
            modifier = Modifier
                .fillMaxHeight(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.hercules),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(320.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.herculespalabras),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text(text = "Username") },
                value = username.value,
                onValueChange = { username.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.login),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

        }
    }
}


@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    Row {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Hello, ")
            Text(text = name)
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EjemploClaseTheme {
        LogInScreen(listOf("World", "Compose"))
    }
}