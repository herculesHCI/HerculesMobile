package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun Discover(){
    AppBar(){
        DiscoverContent()
    }

}

val items = listOf("Diego´s Workout", "Nono´s Workout", "Tute´s Workout", "ABRAKADABRA","Noel Workout")
val exercises = listOf("Diego´s Workout", "Nono´s Workout", "Tute´s Workout", "ABRAKADABRA","Noel Workout");


@Composable
fun DiscoverContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)
        .verticalScroll(rememberScrollState())){
        Column(){
            val searchField = remember { mutableStateOf(TextFieldValue()) }

            /** SEARCH BAR
             * La dejo aca para acceder mas facil al text field cuando haga falta, se puede cambiar tranquilamente */

            /** SEARCH BAR
             * La dejo aca para acceder mas facil al text field cuando haga falta, se puede cambiar tranquilamente */
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .border(3.dp, Color.LightGray, RoundedCornerShape(5.dp))

                ,
                label = {
                    Row( ) {
                        Icon(imageVector = Icons.Default.Search ,
                            contentDescription = null)
                        Text(text = "Search",
                            modifier = Modifier.padding(horizontal = 10.dp))
                        Spacer(modifier = Modifier.fillMaxWidth(.85f))
                        Icon(imageVector = Icons.Default.List,
                            /**CHANGE ICON HERE*/
                            contentDescription = null,)

                    }
                },
                value = searchField.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { searchField.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black)
            )
            for(item in items){
                Row(modifier = Modifier.padding(10.dp)){
                    WorkoutElement(item = item)
                }
            }
        }

    }
}

@Composable
fun WorkoutElement(item: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
            .defaultMinSize()
            .padding(10.dp)
    ){
        Row(){
            Column(modifier = Modifier.weight(0.7f)) {
                Row(){
                    Text(text= item ,
                        fontSize = 17.sp)
                }
                Text(text= "Upper",
                    fontSize = 12.sp)
            }
            Column(){
                Row(){
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Star ,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle ,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) { // TODO Importar paquete de iconos nuevos
                        Icon(imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null)
                    }
                    Text(text = "7.3", textAlign =  TextAlign.Justify) // TODO alinear bien
                }

            }

        }
    }
}