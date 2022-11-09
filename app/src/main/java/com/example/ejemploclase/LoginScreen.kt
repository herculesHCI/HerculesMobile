package com.example.ejemploclase

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview()
@Composable
fun LogInScreen() {
    // A surface container using the 'background' color from the theme

    val coroutineScope= rememberCoroutineScope()
    val focusManager= LocalFocusManager.current
    val bringIntoViewRequester= BringIntoViewRequester()

    Surface(//El surface es el equivalente al div
        modifier = Modifier.fillMaxSize(),
        color =MaterialTheme.colors.primary
    ) {


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
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
                    .padding(10.dp)
                    .onFocusEvent { event->
                        if(event.isFocused){
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }
                ,
                label = { Text(text = "Username") },
                value = username.value,
                onValueChange = { username.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black),
                keyboardOptions=KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction=ImeAction.Next
            ))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .onFocusEvent { event->
                        if(event.isFocused){
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black),
                keyboardOptions=KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction=ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone={focusManager.clearFocus()}
                )
            )
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .bringIntoViewRequester(bringIntoViewRequester),
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