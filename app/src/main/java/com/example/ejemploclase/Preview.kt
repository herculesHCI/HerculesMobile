package com.example.ejemploclase

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun Preview(){
    AppBar(){
        PreviewContent()
    }
}

@Composable
fun PreviewContent(){
    var WorkoutName = "Big Muscles 100% Fast"
    var WorkoutAuthor = "Big Daddy"
    var WorkoutExercises = arrayOf("Psuh Hard", "Daddys Long Legs", "Shake it Up!", "Buttocks Mover", "The Tomato Man")
    var Rating = "4.2"

    Card(shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(15.dp)
            .defaultMinSize(),
        backgroundColor = MaterialTheme.colors.primary
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        {
            Row( modifier = Modifier
                .fillMaxWidth()
                ){

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(vertical = 5.dp)
                    )
                }

                Spacer( modifier = Modifier
                    .weight(1f))



                Text(
                    modifier = Modifier.padding(top=7.dp),
                    text= Rating,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(top = 5.dp),
                )

            }


            Text(
                text= WorkoutName,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "by " + WorkoutAuthor,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 10.dp)
            )


            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = 15.dp)
            ) {
                WorkoutExercises.forEach { str ->
                    Text(
                        text = str,
                        fontSize = 20.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))


        }
    }

}

