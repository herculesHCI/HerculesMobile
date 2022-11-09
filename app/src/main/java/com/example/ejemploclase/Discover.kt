package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Discover(){
    AppBar(){
        DiscoverContent()
    }

}


@Composable
fun DiscoverContent(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)){
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
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/
                            /**CHANGE ICON HERE*/ /**CHANGE ICON HERE*/
                            contentDescription = null,)

                    }
                },
                value = searchField.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { searchField.value = it },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Black)
            )

            WorkoutElement(item = "AndosWorkout")

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
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .defaultMinSize())
                    Icon(
                        imageVector = Icons.Default.Star ,
                        contentDescription = null
                    )
                }

                Text(text="test\ntest\ntest\ntest\ntest\n" )

            }

            Column(){
                Icon(
                    imageVector = Icons.Default.AddCircle ,
                    contentDescription = null
                )
            }

        }
    }
}