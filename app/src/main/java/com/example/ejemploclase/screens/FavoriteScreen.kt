package com.example.ejemploclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejemploclase.AppBar
import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejemploclase.ui.main.canGetFavourites


@Composable
fun FavoriteScreen(navController: NavHostController,viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
) {
    AppBar(navController) {
        FavoriteContent(navController,viewModel)
    }
}

@Composable
fun FavoriteContent(navController: NavHostController,viewModel: MainViewModel = viewModel(
    factory = getViewModelFactory()
)
){
    val uiState = viewModel.uiState
    if(uiState.favouritesRoutines == null || uiState.favChanged){
        viewModel.getFavorites()
        // TODO ERR_MSG No tiene favoritos o error en conexion con la api
    }
    if(viewModel.uiState.canGetFavourites){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 25.dp)
            .verticalScroll(rememberScrollState())){
            Column(){
                Row(modifier = Modifier.padding(15.dp)){
                    Text(text= "Favorite Workouts",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold)
                }
                for(item in viewModel.uiState.favouritesRoutines!!){
                    Row(modifier = Modifier.padding(10.dp)){
                        WorkoutFavElement(item = item,navController)
                    }
                }
            }

        }
    }
}

@Composable
fun WorkoutFavElement(item: Workout,navController: NavHostController,viewModel: MainViewModel = viewModel(
    factory = getViewModelFactory())) {
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
                    ClickableText(text= AnnotatedString(item.name) // tiene fontSize = 17.sp
                        ,onClick = {
                            navController.navigate("preview/${item.id}")
                        } )
                }
                Row(){
                    Text(text= item.category.name,
                        fontSize = 12.sp)
                }
                Row(){
                    Text(text= "By ".plus(item.user.username),
                        fontSize = 12.sp)
                }
            }
            Column(){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(text = item.getScore().toString(), textAlign =  TextAlign.Justify)
                    Icon(
                        imageVector = Icons.Default.StarBorder ,
                        contentDescription = null
                    )
                    IconButton(onClick = {
                        viewModel.deleteFavorite(item.id)
                    }) {
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("workout/${item.id}")
                    }) { //Deberia pasar a la screen de workout
                        Icon(imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }
            }
        }
    }
}