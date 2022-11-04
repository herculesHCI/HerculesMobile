package com.example.ejemploclase

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun LogInScreen() {
    // A surface container using the 'background' color from the theme
    Surface(//El surface es el equivalente al div
        modifier = Modifier.fillMaxSize(),
        color =MaterialTheme.colors.primary
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
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black)
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
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black)
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