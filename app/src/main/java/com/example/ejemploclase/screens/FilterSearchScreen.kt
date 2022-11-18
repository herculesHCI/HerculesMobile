package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 25.dp, vertical = 10.dp))
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
            for(i in 0 until ((categories.size)/4+1) ){
                Row( modifier = Modifier.padding(vertical = 5.dp)){
                    for(j in (i*4) until (i*4+4)  ){
                        if( categories.size > j){
                            Button(onClick = {
                                navController.navigate("discover/${categories[j]}")
                            }){
                                Text(text = categories[j])
                            }
                            //WordContainer(name = categories[j])
                            Spacer( modifier = Modifier
                                .width(30.dp)
                                .height(30.dp) )
                        }
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
            for(i in 0 until ((categories2.size)/2+1) ){
                Row( modifier = Modifier.padding(vertical = 5.dp)){
                    for(j in (i*2) until (i*2+2)  ){
                        if( categories2.size > j){
                            Button(onClick = {
                                navController.navigate("discover/${categories2[j]}")
                            }){
                                Text(text = categories2[j])
                            }
                            //WordContainer(name = categories2[j])
                            Spacer( modifier = Modifier
                                .width(30.dp)
                                .height(30.dp) )
                        }
                    }
                }
            }
        }


    }
}
