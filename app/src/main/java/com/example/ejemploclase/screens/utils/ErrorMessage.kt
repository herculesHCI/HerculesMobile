package com.example.ejemploclase.screens.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(message: String){
    Column() {
        Row(modifier = Modifier.padding(15.dp)) {
            Text(
                text = message,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Icon(
            imageVector = Icons.Default.Checklist,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color.Black
        )
    }
}