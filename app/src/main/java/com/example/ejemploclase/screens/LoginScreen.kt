package com.example.ejemploclase.screens

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.ejemploclase.R
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import kotlinx.coroutines.delay


@Composable
fun LogInScreen(navController: NavHostController, viewModel: MainViewModel = viewModel(factory = getViewModelFactory())) {
    // A surface container using the 'background' color from the theme

    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
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
                    .padding(10.dp),
                label = { Text(text = "Username") },
                value = username.value,
                onValueChange = { username.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text(text = "Password") },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { password.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        if(!viewModel.uiState.isFetching){
                            viewModel.login(username.value.text , password.value.text)
                            coroutineScope.launch {
                                delay(250)
                                if(viewModel.uiState.isAuthenticated){
                                    navController.navigate("discover")
                                }
                            }
                        }
                    },
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