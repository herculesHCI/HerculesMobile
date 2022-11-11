package com.example.ejemploclase

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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun FavoriteScreen(){
        FavoriteContent()
}

val favItems = listOf(Workout("Diego´s Workout","Ppeito",3.3,"Upper",true,0),
    Workout("Nono´s Workout","Nono",6,"Arms",true,1),
    Workout("Tute´s Workout","El MatiWodtke",10,"Abs",true,2),
    Workout("New Workout","user1",0,"Legs",true,3),
    Workout("Big arms","TheRock",9.8,"Arms",true,4),
    Workout("Back Training","JeffNippard",7,"Back",true,5),
    Workout("Full Body Strength","JohnSmith123",8.2,"Full Body",true,6),
    Workout("Cardio Resistance","tourFanboy",6.7,"Cardio",true,7),
    Workout("yeaaa buddy","RonnieColeman",4.3,"Legs",true,8)
)

@Composable
fun FavoriteContent(){
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
            for(item in favItems){
                Row(modifier = Modifier.padding(10.dp)){
                    WorkoutFavElement(item = item)
                }
            }
        }

    }
}

@Composable
fun WorkoutFavElement(item: Workout) {
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
                    IconButton(onClick = { /*TODO*/ }) { //Deberia sacarlo de la screen de fav
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    IconButton(onClick = {/*TODO*/}) { //Deberia pasar a la screen de workout
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