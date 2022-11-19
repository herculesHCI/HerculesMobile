package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun FilterScreen(navController: NavHostController){
    AppBarCompact(navController = navController) {
        FilterContent(navController)
    }
}

@Composable
fun FilterContent(navController: NavHostController){

    val categories =  arrayOf(stringResource(R.string.name_back),stringResource(R.string.name_legs),stringResource(R.string.name_push),stringResource(R.string.name_pull),
        stringResource(R.string.name_upper),stringResource(R.string.name_abs),stringResource(R.string.name_arms),stringResource(R.string.name_cardio),stringResource(R.string.name_full_body))

    val categories2 = arrayOf(stringResource(R.string.name_recent),stringResource(R.string.name_highest_rated),stringResource(R.string.name_oldest))

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 10.dp),
        )
        {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(vertical = 10.dp)
                )
            }
            Text(
                text= stringResource(R.string.filter_add),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.filter_body_p),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Column(){
                LazyVerticalGrid(
                    contentPadding = PaddingValues(5.dp),
                    columns = GridCells.Adaptive(100.dp),
                ) {
                    items(9 ) { item ->
                        Button(onClick = {
                            navController.navigate("discover/${categories[item]}")
                        },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.padding(5.dp)){
                            Text(text = categories[item],
                                color = Color.Black)
                        }
                    }
                }
            }
            Text(
                text = stringResource(R.string.filter_other),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Column(){
                LazyVerticalGrid(
                    contentPadding = PaddingValues(5.dp),
                    columns = GridCells.Adaptive(100.dp),
                ) {
                    items(3 ) { item ->
                        Button(onClick = {
                            navController.navigate("discover/${categories2[item]}")
                        },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.padding(5.dp)){
                            Text(text = categories2[item],
                                color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
