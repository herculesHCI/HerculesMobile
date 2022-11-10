package com.example.ejemploclase

import android.graphics.Color
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.logging.Filter

@Preview
@Composable
fun Filter(){
    AppBar(){
        FilterContent()
    }
}


@Composable
fun FilterContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 25.dp, vertical = 10.dp))
    {

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(vertical = 10.dp)
            )
        }

        Text(
            text="Add Filter",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )


        Text(
            text = "Body Parts Categories",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val categories =  arrayOf("Back","Legs","Push","Pull","Upper","Abs","Arms","Cardio","Full Body")
        Column(){
            for(i in 0 until ((categories.size)/4+1) ){
                Row( modifier = Modifier.padding(vertical = 5.dp)){
                    for(j in (i*4) until (i*4+4)  ){
                        if( categories.size > j){
                            WordContainer(name = categories[j])
                            Spacer( modifier = Modifier.width(30.dp).height(30.dp) )
                        }
                    }
                }
            }
        }


        Text(
            text = "Other Categories",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val categories2 = arrayOf("Most recent", " Highest Rated", "Difficulty", "Favorite" )
        Column(){
            for(i in 0 until ((categories2.size)/2+1) ){
                Row( modifier = Modifier.padding(vertical = 5.dp)){
                    for(j in (i*2) until (i*2+2)  ){
                        if( categories2.size > j){
                            WordContainer(name = categories2[j])
                            Spacer( modifier = Modifier.width(30.dp).height(30.dp) )
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun WordContainer(name: String){
    Box(modifier = Modifier
        .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(15.dp))
        .defaultMinSize()
        .padding(5.dp)
    ){
        Text(
            text=name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            )
    }
}


