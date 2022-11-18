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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejemploclase.Filter
import com.example.ejemploclase.R
import com.example.ejemploclase.data.model.Workout
import com.example.ejemploclase.data.network.util.getViewModelFactory
import com.example.ejemploclase.ui.main.MainViewModel
import com.example.ejemploclase.ui.main.canGetRoutines

@Composable
fun DiscoverScreen(navController: NavHostController,filterName: String? = "Highest Rated",viewModel: MainViewModel = viewModel(factory = getViewModelFactory())){
    if(filters.isEmpty()){
        filters.put("Back",Filter("score","desc",1, stringResource(R.string.filter_back)))
        filters.put("Legs",Filter("score","desc",2, stringResource(R.string.filter_leg)))
        filters.put("Push",Filter("score","desc",3, stringResource(R.string.filter_push)))
        filters.put("Pull",Filter("score","desc",4, stringResource(R.string.filter_pull)))
        filters.put("Upper",Filter("score","desc",5, stringResource(R.string.filter_upper)))
        filters.put("Abs",Filter("score","desc",6, stringResource(R.string.filter_ab)))
        filters.put("Arms",Filter("score","desc",7, stringResource(R.string.filter_arm)))
        filters.put("Cardio",Filter("score","desc",8, stringResource(R.string.filter_cardio)))
        filters.put("Full Body",Filter("score","desc",9, stringResource(R.string.filter_fullbody)))
        filters.put("Most Recent", Filter("date","desc",null, stringResource(R.string.filter_recent)))
        filters.put("Highest Rated", Filter("score","desc",null, stringResource(R.string.filter_highest_rated)))
        filters.put("Oldest Workouts", Filter("date","asc",null, stringResource(R.string.filter_oldest)))
    }
    com.example.ejemploclase.AppBar(navController) {
        filters[filterName]?.let { DiscoverContent(it, viewModel, navController) }
    }
}
val filters : HashMap<String,Filter> = HashMap<String,Filter>()

@Composable
fun DiscoverContent(filter: Filter, viewModel: MainViewModel, navController: NavHostController){
    val uiState = viewModel.uiState
    if(!uiState.canGetRoutines && !uiState.isFetching){
        viewModel.getRoutines(filter.orderBy,filter.direction,filter.categoryId)
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
                Text(text= filter.title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    navController.navigate("filter")
                }) { //Deberia mandarte a la screen de filters
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
                    Text(text= stringResource(R.string.by).plus(item.user.username),
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
                }
            }
        }
    }
}