package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DiscoverScreen(){
    AppBar(){
        DiscoverContent(Filter("Top kkita","Most Recent"))
    }
}
val items = listOf(Workout("Diego´s Workout","Ppeito",3.3,"Upper",true),
    Workout("Nono´s Workout","Nono",6,"Arms",true),
    Workout("Tute´s Workout","El MatiWodtke",10,"Abs",false),
    Workout("New Workout","user1",0,"Legs",false),
    Workout("Big arms","TheRock",9.8,"Arms",true),
    Workout("Back Training","JeffNippard",7,"Back",false),
    Workout("Full Body Strength","JohnSmith123",8.2,"Full Body",true),
    Workout("Cardio Resistance","tourFanboy",6.7,"Cardio",false),
    Workout("yeaaa buddy","RonnieColeman",4.3,"Legs",false)
)

@Composable
fun DiscoverContent(filter: Filter){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(horizontal = 25.dp)
        .verticalScroll(rememberScrollState())){
        Column(){
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text= filter.getTitle(),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) { //Deberia mandarte a la screen de filters
                    Icon(imageVector = Icons.Default.FilterList,
                        contentDescription = null)
                }
            }
            for(item in items){
                Row(modifier = Modifier.padding(10.dp)){
                    WorkoutElement(item = item)
                }
            }
        }

    }
}

@Composable
fun WorkoutElement(item: Workout) {
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
                    ClickableText(text= AnnotatedString(item.getName()) // tiene fontSize = 17.sp
                        ,onClick = {/*TODO Hacer que cambie de screen*/} )
                }
                Row(){
                    Text(text= item.getCategoryName(),
                        fontSize = 12.sp)
                }
                Row(){
                    Text(text= "By ".plus(item.getAuthor()),
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
                    IconButton(onClick = { /*TODO*/ }) {//Deberia marcarlo como fav
                        if(item.getIsFav()){
                            Icon(imageVector = Icons.Default.Favorite,
                                contentDescription = null)
                        } else {
                            Icon(imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}