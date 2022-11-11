package com.example.ejemploclase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun PreviewScreen(){
    var workout = Workout("Big Mucle Factory Baby", "Big Daddy", 7.5 ,
            "Chest", false  , 4 )

    var cycle = Cycle("x Cycle", "abs", 7 , 3 )
    cycle.setExercises( listOf( Exercise("Jumping Jacks", 5,10 ) ,
            Exercise("Jumping Jacks", 5,10 ),
            Exercise("Jumping Jacks", 5,10 ) ))

    workout.setCycles( listOf(cycle, cycle,cycle) )

    Box( modifier = Modifier.background(MaterialTheme.colors.background)){

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                            .padding(15.dp)
                            .size(40.dp)

            )
        }

        Card(shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 65.dp)
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

                    Text(
                            modifier = Modifier.padding(top=7.dp),
                            text= workout.getCategoryName(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                    )


                    Spacer( modifier = Modifier
                            .weight(1f))



                    Text(
                            modifier = Modifier.padding(top=7.dp),
                            text= workout.getScore().toString(),
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
                        text= workout.getName(),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                )

                Text(
                        text = "by " + workout.getAuthor(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 10.dp)
                )


                Column(
                        modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(top = 15.dp)
                ) {
                    workout.getCycles()?.forEach { cyc ->
                        Text(
                                text = cyc.getName(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium
                        )

                        cyc.getExercises()?.forEach(){ ex ->
                            Text(
                                    text = ex.getName(),
                                    fontSize = 20.sp,
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                    }

                }

                Spacer(modifier = Modifier.height(50.dp))


            }
        }
    }


}

