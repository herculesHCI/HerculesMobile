package com.example.ejemploclase

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejemploclase.ui.theme.EjemploClaseTheme
import java.time.format.TextStyle

@Preview
@Composable
fun SetupGraph()
{
    Scaffold(
            topBar = { TopBar() },
    bottomBar = { BottomBar()}
    ) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)){
        Column(){
            val searchField = remember { mutableStateOf(TextFieldValue()) }

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
                        Icon(imageVector = Icons.Default.List, /**CHANGE ICON HERE*/
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
}


@Composable
fun TopBar(){
    TopAppBar(
        backgroundColor = Color.LightGray,
        title = { Text( text = "Hercules") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings ,
                    contentDescription = null,
                ) //Ver Como verga mover hacia la derecha
            }
        }
    )
}


@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp ,
                    backgroundColor = Color.LightGray) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Discover") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.PlayArrow,"")
        },
            label = { Text(text = "Workout") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
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
                    Text(text= ".... Workout", modifier = Modifier
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