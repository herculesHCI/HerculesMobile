package com.example.ejemploclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejemploclase.Filter
import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import com.example.ejemploclase.ui.main.canGetRoutines

@Composable
fun DiscoverScreen(navController: NavHostController,viewModel: MainViewModel = viewModel(factory = getViewModelFactory())){
    com.example.ejemploclase.AppBar(navController) {
        DiscoverContent(Filter("Top kkita", "Most Recent"), viewModel, navController)
    }
}

@Composable
fun DiscoverContent(filter: Filter, viewModel: MainViewModel, navController: NavHostController){
    val uiState = viewModel.uiState
    if(!uiState.canGetRoutines){
        viewModel.getRoutines("date","asc",null)
        // TODO ERR_MSG si routines es NULL
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)
        .verticalScroll(rememberScrollState())){
        Column(){
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)){
                Text(text= filter.getTitle(),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) { //Deberia mandarte a la screen de filters
                    Icon(imageVector = Icons.Default.FilterList,
                        contentDescription = null)
                }
            }
            if(uiState.routines != null){
                for(item in uiState.routines){
                    Row(modifier = Modifier.padding(10.dp)){
                        WorkoutElement(item = item,navController)
                    }
                }
            }
        }

    }

}

@Composable
fun WorkoutElement(item: Workout,navController: NavHostController) {
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
                    Text(text = item.score.toString(), textAlign =  TextAlign.Justify)
                    Icon(
                        imageVector = Icons.Default.StarBorder ,
                        contentDescription = null
                    )
                    IconButton(onClick = { /*TODO*/ }) {//Deberia marcarlo como fav
                        if(true){ // TODO ver si es fav o no
                            Icon(imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary)
                        } else {
                            Icon(imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary)
                        }
                    }
                }
            }
        }
    }
}